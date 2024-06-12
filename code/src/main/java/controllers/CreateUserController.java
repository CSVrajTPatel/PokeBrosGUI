package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Facade;
import pokebros.App;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
public class CreateUserController {

    @FXML
    private Button CreateUser;

    @FXML
    private TextField email;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField newPassword;

    @FXML
    private TextField newUsername;

    // Method to handle the creation of a new user
    @FXML
    void handleCreateUser(ActionEvent event) throws IOException {
        String username = newUsername.getText();
        String password = newPassword.getText();
        String userEmail = email.getText();
        String userFirstName = firstName.getText();
        String userLastName = lastName.getText();

        // Validate inputs
        if (username.isEmpty() || password.isEmpty() || userEmail.isEmpty() || userFirstName.isEmpty() || userLastName.isEmpty()) {
            showAlert("Validation Error", "Please fill in all fields.");
            return;
        }

        if (!isValidEmail(userEmail)) {
            showAlert("Validation Error", "Please enter a valid email address.");
            return;
        }

        Facade facade = Facade.getInstance();
        if (facade.createUser(username, password, userFirstName, userLastName, userEmail)) {
            facade = Facade.getInstance(username, password);

            if (facade.getUser() == null) {
                showAlert("Error", "User creation failed.");
                return;
            }

             try {
                App.setRoot("tabView");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showAlert("Validation Error", "Username Taken.");
        }
    }

    // Method to show alert dialog
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Method to validate email address
    private boolean isValidEmail(String email) {
        // Basic email validation logic
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
}
