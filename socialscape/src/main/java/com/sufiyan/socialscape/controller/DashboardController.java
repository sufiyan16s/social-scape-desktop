package com.sufiyan.socialscape.controller;

import com.sufiyan.socialscape.dao.PostDAO;
import com.sufiyan.socialscape.model.Post;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaView;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class DashboardController {

    @FXML private TextField tfTitle, tfPlatform, tfCaption, tfDate, tfTime;
    @FXML private TableView<Post> tablePosts;
    @FXML private TableColumn<Post, String> colTitle, colPlatform, colDate, colTime, colStatus;
    @FXML private Label lblFileName;
    @FXML private ImageView imgPreview;
    @FXML private MediaView videoPreview;
    @FXML private Label lblStatus;
    @FXML private TextField tfSearch;
    @FXML private DatePicker dateFilter;

    private int loggedInUserId = 1;  // Replace with session

    private ObservableList<Post> postList = FXCollections.observableArrayList();



    private String selectedMediaPath = "";
    private String selectedMediaType = "";
    private Post editingPost = null;
    private FilteredList<Post> filteredList;

    @FXML
    public void initialize() {
        colTitle.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTitle()));
        colPlatform.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getPlatform()));
        colDate.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDate()));
        colTime.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTime()));
        colStatus.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getStatus()));
        loadPosts();
        addContextMenu();

        filteredList = new FilteredList<>(postList, p -> true);
        tablePosts.setItems(filteredList);
    }

    private void loadPosts() {
        postList.setAll(PostDAO.getPostsByUser(loggedInUserId));
        tablePosts.setItems(postList);

    }

    private void addContextMenu() {
        ContextMenu menu = new ContextMenu();
        MenuItem edit = new MenuItem("Edit");
        MenuItem delete = new MenuItem("Delete");

        edit.setOnAction(e -> handleEditPost());
        delete.setOnAction(e -> handleDeletePost());

        menu.getItems().addAll(edit, delete);
        tablePosts.setContextMenu(menu);
    }

    public void handleAddPost() {
        if(editingPost != null) {
            editingPost.setTitle(tfTitle.getText());
            editingPost.setPlatform(tfPlatform.getText());
            editingPost.setCaption(tfCaption.getText());
            editingPost.setDate(tfDate.getText());
            editingPost.setTime(tfTime.getText());
            editingPost.setStatus(selectedMediaPath);
            editingPost.setMediaType(selectedMediaType);
            editingPost.setStatus("scheduled");

            PostDAO.update(editingPost);
            editingPost = null;
            lblStatus.setText("Post updated successfully!");
        } else {
            Post post = new Post();
            post.setUserId(loggedInUserId);
            post.setTitle(tfTitle.getText());
            post.setPlatform(tfPlatform.getText());
            post.setCaption(tfCaption.getText());
            post.setDate(tfDate.getText());
            post.setTime(tfTime.getText());
            post.setMediaPath(selectedMediaPath); // to be added later
            post.setMediaType(selectedMediaType); // Optional
            post.setStatus("scheduled");

            PostDAO.insert(post);
            lblStatus.setText("Post added!");
        }
            clearForm();
            loadPosts();

    }

    @FXML
    public void handleChooseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image or Video");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png"),
                new FileChooser.ExtensionFilter("Video Files", "*.mp4", "*.mov", "*.avi")
        );

        File selectedFile = fileChooser.showOpenDialog(null);
        if(selectedFile != null) {
            try {
                String ext = selectedFile.getName().substring(selectedFile.getName().lastIndexOf("."));
                String fileName = "media_" + System.currentTimeMillis() + ext;
                Path dest = Paths.get("C:/Users/sufiy/socialscape/src/main/resources/media/", fileName);

                Files.copy(selectedFile.toPath(), dest, StandardCopyOption.REPLACE_EXISTING);

                selectedMediaPath = dest.toString();
                selectedMediaType = ext.equals(".mp4") ? "video" : "image";

                lblFileName.setText("\uD83D\uDCC1" + fileName);

                if(selectedMediaType.equals("image")) {
                    imgPreview.setImage(new Image(dest.toUri().toString()));
                    imgPreview.setVisible(true);
                    videoPreview.setVisible(false);
                } else {
                    Media media = new Media(dest.toUri().toString());
                    MediaPlayer mediaPlayer = new MediaPlayer(media);
                    videoPreview.setMediaPlayer(mediaPlayer);
                    videoPreview.setVisible(true);
                    imgPreview.setVisible(false);
                    mediaPlayer.setAutoPlay(true);
                }
            } catch (IOException e) {
                lblFileName.setText("Error saving file");
                e.printStackTrace();
            } catch (Exception e) {
                lblFileName.setText("Error loading preview");
                e.printStackTrace();
            }
        }
    }

    private void clearForm() {
        tfTitle.clear();
        tfPlatform.clear();
        tfCaption.clear();
        tfDate.clear();
        tfTime.clear();
    }

    @FXML
    private void handleEditPost() {
        Post selected = tablePosts.getSelectionModel().getSelectedItem();
        if(selected == null) return;

        tfTitle.setText(selected.getTitle());
        tfPlatform.setText(selected.getPlatform());
        tfCaption.setText(selected.getCaption());
        tfDate.setText(selected.getDate());
        tfTime.setText(selected.getTime());

        selectedMediaPath = selected.getMediaPath();
        selectedMediaType = selected.getMediaType();
        editingPost = selected;

        lblFileName.setText("Loaded media from: " + selected.getMediaPath());
    }

    @FXML
    private void handleDeletePost() {
        Post selected = tablePosts.getSelectionModel().getSelectedItem();
        if(selected == null) return;

        boolean confirmed = new Alert(Alert.AlertType.CONFIRMATION, "Delete this post?", ButtonType.YES, ButtonType.NO)
                .showAndWait()
                .filter(response -> response == ButtonType.YES)
                .isPresent();

        if(confirmed) {
            PostDAO.delete(selected.getId());
            loadPosts();
            lblStatus.setText("Post deleted.");
        }
    }

    @FXML
    public void handleExportCSV() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export CSV File");
        fileChooser.setInitialFileName("socialscape-post.csv");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));

        File file = fileChooser.showSaveDialog(null);
        if(file != null) {
            try (PrintWriter writer = new PrintWriter(file)) {
                writer.println("Title, Platform, Caption, Date, Time, Status");

                for(Post post : postList) {
                    writer.printf("%s,%s,%s,%s,%s,%s%n",
                            post.getTitle(),post.getPlatform(), post.getCaption(),
                            post.getDate(), post.getTime(), post.getStatus());
                }

                showAlert("Exported CSV successfully!");
            } catch (Exception e) {
                showAlert("Failed to export CSV: " + e.getMessage());
            }
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK);
        alert.showAndWait();
    }

    @FXML
    private void filterPosts() {
        String keyword = tfTitle.getText().toLowerCase().trim();

        filteredList.setPredicate(post -> {
            if (keyword.isEmpty()) return true;

            return post.getTitle().toLowerCase().contains(keyword)
                    || post.getPlatform().toLowerCase().contains(keyword)
                    || post.getCaption().toLowerCase().contains(keyword);
        });

    }

    @FXML
    public void filterByDate() {
        if(dateFilter.getValue() != null) {
            String dateStr = dateFilter.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            filteredList.setPredicate(post -> post.getDate().equals(dateStr));
        }
    }

    @FXML
    private void handleLogout() {
        try {
            Parent loginRoot = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
            Stage stage = (Stage) tfTitle.getScene().getWindow();
            stage.setScene(new Scene(loginRoot));
            stage.setTitle("Social Scape - Login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
