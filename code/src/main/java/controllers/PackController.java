package controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Card;
import model.Pack;
import pokebros.App;

public class PackController {

    @FXML
    private Button openPackButton;

    @FXML
    private VBox cardContainer;

    @FXML
    private Button switchSC;

    @FXML
    private Button switchT;

    @FXML
    private Button switchC;

    @FXML
    void initialize() {
        // Initialization logic if needed
    }

    @FXML
    void openPack(ActionEvent event) {
        Pack pack = new Pack(1); // Assume pack number 1 for simplicity
        ArrayList<Card> cards = pack.openPack();

        cardContainer.getChildren().clear();
        for (Card card : cards) {
            cardContainer.getChildren().add(createCardBox(card));
        }
    }

    private VBox createCardBox(Card card) {
        VBox cardBox = new VBox();
        cardBox.setSpacing(5);

        ImageView imageView = new ImageView();
        imageView.setFitHeight(200);
        imageView.setFitWidth(144);

        String imagePath = "/pokebros/Images/pokemon/" + card.getId() + ".png";
        try (InputStream imageStream = getClass().getResourceAsStream(imagePath)) {
            if (imageStream != null) {
                Image image = new Image(imageStream);
                imageView.setImage(image);
            } else {
                System.err.println("Image not found: " + imagePath);
                imageView.setImage(null);
            }
        } catch (IOException e) {
            e.printStackTrace();
            imageView.setImage(null);
        }

        cardBox.getChildren().add(imageView);
        return cardBox;
    }

    @FXML
    void switchToSearchCards(ActionEvent event) {
        switchScene("SearchCards.fxml");
    }

    @FXML
    void switchToTrade(ActionEvent event) {
        switchScene("Trade.fxml");
    }

    @FXML
    void switchToCollection(ActionEvent event) {
        switchScene("MyCollection.fxml");
    }

    private void switchScene(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pokebros/" + fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) openPackButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
