package controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Card;
import model.Facade;

public class SearchCardsController {

    @FXML
    private AnchorPane DispFiltered;

    @FXML
    private CheckBox ID;

    @FXML
    private RadioButton R_Double_Rare;

    @FXML
    private RadioButton R_Hyper_Rare;

    @FXML
    private RadioButton R_Illustration;

    @FXML
    private RadioButton R_RARE;

    @FXML
    private RadioButton R_Special;

    @FXML
    private RadioButton R_ULtra_Rare;

    @FXML
    private ToggleGroup Rarity;

    @FXML
    private TextField Searchbar;

    @FXML
    private RadioButton T_Darkness;

    @FXML
    private RadioButton T_Dragon;

    @FXML
    private RadioButton T_Fairy;

    @FXML
    private RadioButton T_Fighting;

    @FXML
    private RadioButton T_Fire;

    @FXML
    private RadioButton T_Grass;

    @FXML
    private RadioButton T_Lightning;

    @FXML
    private RadioButton T_Metal;

    @FXML
    private RadioButton T_Psychic;

    @FXML
    private RadioButton T_Water;

    @FXML
    private ToggleGroup Type;

    @FXML
    private Button switchMC1;

    @FXML
    private Button switchMC11;

    @FXML
    void initialize() {
        displayAllCards();
    }

    private void displayAllCards() {
        Facade facade = Facade.getInstance();
        VBox container = new VBox(10);  // Vertical container for rows of cards

        HBox currentRow = new HBox(10);
        int cardCount = 1;

        for (Card card : facade.getCardList()) {
            if (card.getId() == 0) continue;

            if (cardCount % 8 == 1 && cardCount != 1) {
                container.getChildren().add(currentRow);
                currentRow = new HBox(10);
            }
            VBox cardBox = createCardBox(card);
            currentRow.getChildren().add(cardBox);
            cardCount++;
        }

        if (!currentRow.getChildren().isEmpty()) {
            container.getChildren().add(currentRow);
        }

        DispFiltered.getChildren().add(container);
    }

    private VBox createCardBox(Card card) {
        VBox cardBox = new VBox(5);
        ImageView imageView = new ImageView();
        imageView.setFitHeight(195);
        imageView.setFitWidth(140);

        String imagePath = "/pokebros/Images/pokemon/" + card.getId() + ".png";
        try (InputStream imageStream = getClass().getResourceAsStream(imagePath)) {
            if (imageStream != null) {
                Image image = new Image(imageStream);
                imageView.setImage(image);
            } else {
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
    void Searchbar(ActionEvent event) {
        String query = Searchbar.getText().trim();
        if (!query.isEmpty()) {
            displayFilteredCards(query);
        }
    }

    private void displayFilteredCards(String name) {
        Facade facade = Facade.getInstance();
        ArrayList<Card> filteredCards = facade.searchByName(name);
        
        VBox container = new VBox(10);  // Vertical container for rows of cards
        HBox currentRow = new HBox(10);
        int cardCount = 1;

        for (Card card : filteredCards) {
            if (card.getId() == 0) continue;

            if (cardCount % 8 == 1 && cardCount != 1) {
                container.getChildren().add(currentRow);
                currentRow = new HBox(10);
            }
            VBox cardBox = createCardBox(card);
            currentRow.getChildren().add(cardBox);
            cardCount++;
        }

        if (!currentRow.getChildren().isEmpty()) {
            container.getChildren().add(currentRow);
        }

        DispFiltered.getChildren().clear();
        DispFiltered.getChildren().add(container);
    }

    @FXML
    void FR_Illustration(ActionEvent event) {

    }
    
    @FXML
    void FR_Double_Rare(ActionEvent event) {

    }

    @FXML
    void FR_Hyper_Rare(ActionEvent event) {

    }

    @FXML
    void FR_RARE(ActionEvent event) {

    }

    @FXML
    void FR_Special(ActionEvent event) {

    }

    @FXML
    void FR_ULtra_Rare(ActionEvent event) {

    }

    @FXML
    void FT_Darkness(ActionEvent event) {

    }

    @FXML
    void FT_Dragon(ActionEvent event) {

    }

    @FXML
    void FT_Fairy(ActionEvent event) {

    }

    @FXML
    void FT_Fire(ActionEvent event) {

    }

    @FXML
    void FT_Grass(ActionEvent event) {

    }

    @FXML
    void FT_Lighting(ActionEvent event) {

    }

    @FXML
    void FT_Lightning(ActionEvent event) {

    }

    @FXML
    void FT_Metal(ActionEvent event) {

    }

    @FXML
    void FT_Psychic(ActionEvent event) {

    }

    @FXML
    void FT_Water(ActionEvent event) {

    }

    @FXML
    void SID(ActionEvent event) {

    }
}