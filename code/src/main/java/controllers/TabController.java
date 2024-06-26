package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Facade;
import pokebros.App;

public class TabController implements Initializable{

    @FXML
    private Pane mainPane;

    @FXML
    private Button switchSC;

    @FXML
    private Button switchT;

    @FXML
    private Button switchMC;

    @FXML
    private Button logOut;

    @FXML
    private Label userName;

    @FXML
    private Label currency;

    @FXML
    void myCollection(ActionEvent event) {
        mainPane.getChildren().clear();
        try {
            mainPane.getChildren().add(App.loadFXML("MyCollection"));
        } catch (IOException e) {
            System.out.println("Error loading My Collection view:");
            e.printStackTrace();
        }
    }
    

    @FXML
    void packs(ActionEvent event) throws IOException {
        mainPane.getChildren().clear();
        mainPane.getChildren().add(App.loadFXML("Pack"));
    }

    @FXML
    void searchCards(ActionEvent event) throws IOException {
        mainPane.getChildren().clear();
        mainPane.getChildren().add(App.loadFXML("SearchCards"));
    }

    @FXML
    void trade(ActionEvent event) throws IOException {
        mainPane.getChildren().clear();
        mainPane.getChildren().add(App.loadFXML("Trade"));
    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
        Facade facade = Facade.getInstance();
        facade.logOffUser(); // Log off the user

        // Close the current stage
        Stage stage = (Stage) logOut.getScene().getWindow();
        stage.close();
    }

    @FXML
    void claim(ActionEvent event) throws IOException {
        Facade facade = Facade.getInstance();
        facade.claimDailyCurrency();
        currency.setText(String.valueOf(facade.getCurrency()));
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        mainPane.getChildren().clear();
        Facade facade = Facade.getInstance();
        userName.setText(facade.getUserName());
        currency.setText(String.valueOf(facade.getCurrency()));
        try {
            mainPane.getChildren().add(App.loadFXML("MyCollection"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
