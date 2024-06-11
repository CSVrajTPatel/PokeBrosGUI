package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import model.Facade;
import model.Card;
import model.User;

import java.io.IOException;
import java.io.InputStream;

public class MyCollectionController {

    @FXML
    private ListView<Card> listViewCards;

    private User user;

    public void setUser(User user) {
        this.user = user;
        initialize();
    }

    @FXML
    public void initialize() {
        Facade facade = Facade.getInstance();

        // Convert the ArrayList to an ObservableList
        ObservableList<Card> observableCardList = FXCollections.observableArrayList(facade.getOwnedCards());
        listViewCards.setItems(observableCardList);

        // Inline custom cell factory
        listViewCards.setCellFactory(param -> new ListCell<Card>() {
            private HBox content;
            private Text name;
            private ImageView imageView;

            {
                name = new Text();
                imageView = new ImageView();
                imageView.setFitHeight(100); // Set appropriate size for images
                imageView.setFitWidth(100);
                content = new HBox(imageView, name);
                content.setSpacing(10);
            }

            @Override
            protected void updateItem(Card card, boolean empty) {
                super.updateItem(card, empty);
                if (card != null && !empty) {
                    name.setText(card.getName());
                    // Assuming images are stored in the resources/images directory
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
                    setGraphic(content);
                } else {
                    setGraphic(null);
                }
            }
        });
    }
}
