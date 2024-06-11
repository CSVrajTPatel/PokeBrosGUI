package controllers;

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

import java.io.IOException;

public class PrimaryController {

    @FXML
    private Button primaryButton;

    @FXML
    private TextField txtPass;

    @FXML
    private TextField txtUsername;

    @FXML
    private Label lbl_error;

    @FXML
    void login(ActionEvent event) {
        String username = txtUsername.getText();
        String password = txtPass.getText();

        System.out.println("Username is " + username);

        Facade facade = Facade.getInstance(username, password);

        User userCheck = facade.getUser();
        if (userCheck == null) {
            System.out.println("Invalid Username");
            lbl_error.setText("Invalid login credentials.");
            return;
        }

        // Load Card Collection view
        try {
            System.out.println("Loading Card Collection View...");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pokebros/MyCollection.fxml"));
            Parent root = loader.load();
            
            // Optionally, pass any necessary data to the new controller
            MyCollectionController controller = loader.getController();
            controller.setUser(userCheck);

            Scene scene = new Scene(root);
            Stage stage = (Stage) primaryButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            System.out.println("Switched to Card Collection View.");
        } catch (IOException e) {
            e.printStackTrace();
            lbl_error.setText("Failed to load card collection view.");
        }
    }
}
