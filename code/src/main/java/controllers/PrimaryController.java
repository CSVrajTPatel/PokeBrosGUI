package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Facade;
import model.User;

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
        System.out.println("WOOHOOO");
        return;
        
    }

}