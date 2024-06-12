package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.control.Button;

public class PackController {

    @FXML
    private Button switchMC;

    @FXML
    private Button switchSC;

    @FXML
    private Button switchPacks;

    @FXML
    private Button switchT;

    @FXML
    private void switchToMyCollection(ActionEvent event) throws IOException {
        Parent myCollectionParent = FXMLLoader.load(getClass().getResource("MyCollection.fxml"));
        Scene myCollectionScene = new Scene(myCollectionParent);
        Stage window = (Stage) switchMC.getScene().getWindow();
        window.setScene(myCollectionScene);
        window.show();
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
    private void switchToOpenPack(ActionEvent event) throws IOException {
        Parent openPackParent = FXMLLoader.load(getClass().getResource("OpenPack.fxml"));
        Scene openPackScene = new Scene(openPackParent);
        Stage window = (Stage) switchPacks.getScene().getWindow();
        window.setScene(openPackScene);
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
}
