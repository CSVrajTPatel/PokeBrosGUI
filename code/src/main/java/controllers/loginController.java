package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Facade;
import model.User;
import pokebros.App;

public class loginController {
    @FXML
    private Button goToSignUp;

    @FXML
    private TextField password;

    @FXML
    private Button Login;

    @FXML
    private TextField userName;

    @FXML
    private Label lbl_error;

    @FXML
    void Login(ActionEvent event) {
        String username = userName.getText();
        String passwordText = password.getText();

        System.out.println("Username is " + username);

        // Validate input fields
        if (username.isEmpty() || passwordText.isEmpty()) {
            lbl_error.setText("Please enter both username and password.");
            return;
        }

        Facade facade = Facade.getInstance(username, passwordText);

        User userCheck = facade.getUser();
        if (userCheck == null) {
            System.out.println("Invalid Username");
            lbl_error.setText("Invalid login credentials.");
            return;
        }

        // Load Card Collection view
        try {
            App.setRoot("tabView");
        } catch (IOException e) {
            e.printStackTrace();
            lbl_error.setText("Failed to load card collection view.");
        }
    }

    @FXML
    void goToSignUp(ActionEvent event) {
        try {
            System.out.println("Loading Sign Up View...");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pokebros/CreateUser.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = (Stage) goToSignUp.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            System.out.println("Switched to Sign Up View.");
        } catch (IOException e) {
            e.printStackTrace();
            lbl_error.setText("Failed to load sign up view.");
        }
    }
}