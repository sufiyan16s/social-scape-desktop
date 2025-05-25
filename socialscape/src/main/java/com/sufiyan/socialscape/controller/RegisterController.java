package com.sufiyan.socialscape.controller;

import com.sufiyan.socialscape.dao.UserDAO;
import com.sufiyan.socialscape.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;


public class RegisterController {

    @FXML private TextField tfName, tfEmail;
    @FXML private PasswordField tfPassword;
    @FXML private Label lblStatus;

    public void handleRegister() {
        String name = tfName.getText();
        String email = tfEmail.getText();
        String pass = tfPassword.getText();

        if(name.isEmpty() || email.isEmpty() || pass.isEmpty()) {
            lblStatus.setText("Please fill all fields.");
            return;
        }

        User user = new User(name, email, pass, "user");
        boolean success = UserDAO.register(user);

        if(success) {
            lblStatus.setText("Registered successfully!");

        } else {
            lblStatus.setText("Email may already be used.");
        }
    }

    @FXML
    private void handleGoToLogin() throws IOException {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
        Stage stage = (Stage) lblStatus.getScene().getWindow();
        stage.setScene(new javafx.scene.Scene(loginRoot));
        stage.setTitle("Login");
    }

}
