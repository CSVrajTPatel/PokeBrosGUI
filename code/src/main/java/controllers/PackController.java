package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.Facade;
import pokebros.App;

public class PackController {

    @FXML
    private Button Pack1;

    @FXML
    private Button Pack2;

    @FXML
    private Button Pack3;

    @FXML
    void openPack1(ActionEvent event) throws IOException {
        Facade facade = Facade.getInstance();
        facade.openPack(1);
        App.setRoot("OpenPack");
    }

    @FXML
    void openPack2(ActionEvent event) throws IOException {
        Facade facade = Facade.getInstance();
        facade.openPack(2);
        App.setRoot("OpenPack");
    }

    @FXML
    void openPack3(ActionEvent event) throws IOException {
        Facade facade = Facade.getInstance();
        facade.openPack(3);
        App.setRoot("OpenPack");

    }

}
