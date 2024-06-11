package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Facade;
import model.Card;
import model.User;

import java.io.IOException;
import java.io.InputStream;

public class MyCollectionController {

    @FXML
    private HBox hBox;

    private User user;

    public void setUser(User user) {
        this.user = user;
        initialize();
    }

    @FXML
    public void initialize() {
        Facade facade = Facade.getInstance();

        // Clear the HBox to avoid duplicate entries on re-initialization
        hBox.getChildren().clear();

        // Retrieve the owned cards
        for (Card card : facade.getOwnedCards()) {
            VBox cardBox = createCardBox(card);
            hBox.getChildren().add(cardBox);
        }
    }

    private VBox createCardBox(Card card) {
        VBox cardBox = new VBox();
        cardBox.setSpacing(5);

        Label name = new Label(card.getName());
        ImageView imageView = new ImageView();
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);

        // Load image
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

        cardBox.getChildren().addAll(imageView, name);
        return cardBox;
    }
}
