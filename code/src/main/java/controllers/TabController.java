package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import pokebros.*;

public class TabController {

    @FXML
    private Pane mainPane;

    @FXML
    private Button switchSC;

    @FXML
    private Button switchT;

    @FXML
    void myCollection(ActionEvent event) throws IOException {
        mainPane.getChildren().add(App.loadFXML("MyCollection"));
    }

    @FXML
    void packs(ActionEvent event) throws IOException {
        mainPane.getChildren().add(App.loadFXML("Pack"));
    }

    @FXML
    void searchCards(ActionEvent event) throws IOException {
        mainPane.getChildren().add(App.loadFXML("SearchCards"));
    }

    @FXML
    void trade(ActionEvent event) throws IOException {
        mainPane.getChildren().add(App.loadFXML("Trade"));
    }

}
