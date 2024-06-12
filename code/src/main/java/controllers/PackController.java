package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Facade;
import model.User;

import java.io.IOException;
import javafx.scene.control.Button;

public class PackController {

    @FXML
    private Button Pack1;

    @FXML
    private Button Pack2;

    @FXML
    private Button Pack3;

    @FXML
    private Button switchMC;

    @FXML
    private Button switchSC;

    @FXML
    private Button switchT;

    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    private void switchToMyCollection(ActionEvent event) throws IOException {
         Facade facade = Facade.getInstance();
        User userCheck = facade.getUser();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pokebros/MyCollection.fxml"));
        Parent root = loader.load();

        // Pass the user to the new controller
        MyCollectionController controller = loader.getController();
        controller.setUser(userCheck);

        Scene scene = new Scene(root);
        Stage stage = (Stage) switchMC.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void switchToSearchCards(ActionEvent event) throws IOException {
        Parent searchCardsParent = FXMLLoader.load(getClass().getResource("SearchCards.fxml"));
        Scene searchCardsScene = new Scene(searchCardsParent);
        Stage window = (Stage) switchSC.getScene().getWindow();
        window.setScene(searchCardsScene);
        window.show();
    }

    @FXML
    private void switchToTrade(ActionEvent event) throws IOException {
        Parent tradeParent = FXMLLoader.load(getClass().getResource("Trade.fxml"));
        Scene tradeScene = new Scene(tradeParent);
        Stage window = (Stage) switchT.getScene().getWindow();
        window.setScene(tradeScene);
        window.show();
    }

    @FXML
    private void openPack1(ActionEvent event) throws IOException {
        switchToOpenPack(event);
    }

    @FXML
    private void openPack2(ActionEvent event) throws IOException {
        switchToOpenPack(event);
    }

    @FXML
    private void openPack3(ActionEvent event) throws IOException {
        switchToOpenPack(event);
    }

    private void switchToOpenPack(ActionEvent event) throws IOException {
        Parent openPackParent = FXMLLoader.load(getClass().getResource("OpenPack.fxml"));
        Scene openPackScene = new Scene(openPackParent);
        Stage window = (Stage) Pack1.getScene().getWindow();
        window.setScene(openPackScene);
        window.show();
    }
}