package controllers;

import java.io.IOException;
import java.io.InputStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Card;
import model.Facade;
import model.User;

public class MyCollectionController {

    @FXML
    private AnchorPane dispMyCollection;

    @FXML
    private AnchorPane dispFav;

    @FXML
    private Button switchP;

    @FXML
    private Button switchSC;

    @FXML
    private Button switchT;


    private User user;

    public void setUser(User user) {
        this.user = user;
        initialize();
    }

    @FXML
    public void initialize() {
        Facade facade = Facade.getInstance();

        // Clear the AnchorPanes to avoid duplicate entries on re-initialization
        dispMyCollection.getChildren().clear();
        dispFav.getChildren().clear();

        // Create an HBox to hold the cards for My Collection
        HBox myCollectionBox = new HBox();
        myCollectionBox.setSpacing(10);

        for (Card card : facade.getOwnedCards()) {
            VBox cardBox = createCardBox(card);
            myCollectionBox.getChildren().add(cardBox);
        }

        // Add the HBox to the dispMyCollection AnchorPane
        dispMyCollection.getChildren().add(myCollectionBox);

        // Create an HBox to hold the cards for Favorite Cards
        HBox favBox = new HBox();
        favBox.setSpacing(10);

        for (Card card : facade.getFavoriteCards()) {
            VBox cardBox = createCardBox(card);
            favBox.getChildren().add(cardBox);
        }

        // Add the HBox to the dispFav AnchorPane
        dispFav.getChildren().add(favBox);
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
    @FXML
    void P(ActionEvent event) {
    
    }

    @FXML
    void SC(ActionEvent event) {
    
    }

    @FXML
    void T(ActionEvent event) {

    }

}