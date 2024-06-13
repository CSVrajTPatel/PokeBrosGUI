package controllers;

import java.io.IOException;
import java.io.InputStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Card;
import model.Facade;
import pokebros.App;

public class OpenPackController {

    @FXML
    private AnchorPane dispNewCards;

    @FXML
    private Button goBack;

    public void initialize() {
        Facade facade = Facade.getInstance();

        dispNewCards.getChildren().clear();

        HBox myCollectionBox = new HBox();
        myCollectionBox.setSpacing(10);

        for (int i = facade.getOwnedCards().size() - 7; i < facade.getOwnedCards().size(); i++) {
            VBox cardBox = createCardBox(facade.getOwnedCards().get(i));
            myCollectionBox.getChildren().add(cardBox);
        }

        dispNewCards.getChildren().add(myCollectionBox);
    }

    private VBox createCardBox(Card card) {
        VBox cardBox = new VBox();
        cardBox.setSpacing(5);

        //Label name = new Label(card.getName());
        ImageView imageView = new ImageView();
        imageView.setFitHeight(210);
        imageView.setFitWidth(152);

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

        cardBox.getChildren().addAll(imageView);
        return cardBox;
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        App.setRoot("tabView");
    }

}