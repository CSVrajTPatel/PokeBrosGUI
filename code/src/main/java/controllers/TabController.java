package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import pokebros.*;

public class TabController implements Initializable{

    @FXML
    private Pane mainPane;

    @FXML
    private Button switchSC;

    @FXML
    private Button switchT;

    @FXML
    void myCollection(ActionEvent event) throws IOException {
        mainPane.getChildren().clear();
        mainPane.getChildren().add(App.loadFXML("MyCollection"));
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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        mainPane.getChildren().clear();
        try {
            mainPane.getChildren().add(App.loadFXML("MyCollection"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
