package com.sufiyan.socialscape.controller;

import com.sufiyan.socialscape.dao.UserDAO;
import com.sufiyan.socialscape.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginController {

    @FXML private TextField tfEmail;
    @FXML private PasswordField tfPassword;
    @FXML private Label lblStatus;

    public void handleLogin() {
        String email = tfEmail.getText();
        String pass = tfPassword.getText();

        if(email.isEmpty() || pass.isEmpty()) {
            lblStatus.setText("Enter email & password");
            return;
        }

        User user = UserDAO.login(email, pass);
        if(user != null) {
            lblStatus.setText("Welcome, " + user.getName());
            // TODO: redirect to dashboard
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dashboard.fxml"));
                Parent dashboardRoot = loader.load();
                Stage stage = (Stage) tfEmail.getScene().getWindow();
                stage.setScene(new Scene(dashboardRoot));
                stage.setTitle("SocialScape - Dashboard");
            } catch (IOException e) {
                lblStatus.setText("Failed to load dashboard");
                e.printStackTrace();
            }
        } else {
            lblStatus.setText("Invalid credentials.");
        }
    }

    @FXML
    public void handleGoToRegister() throws IOException {
        Parent registerRoot = FXMLLoader.load(getClass().getResource("/view/register.fxml"));
        Stage stage = (Stage) tfEmail.getScene().getWindow();
        stage.setScene(new Scene(registerRoot));
        stage.setTitle("SocialScape - Register");
    }
}
