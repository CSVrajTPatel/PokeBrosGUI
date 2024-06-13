package controllers;

import java.io.IOException;
import java.io.InputStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.Card;
import pokebros.App;

public class CardInfoController {

    @FXML
    private Button AddFavCard;

    @FXML
    private Text Attacks;

    @FXML
    private Button BackToSearch;

    @FXML
    private Text EvoStage;

    @FXML
    private Text Family;

    @FXML
    private Text Pack;

    @FXML
    private Text PokeName;

    @FXML
    private Text PokeRarity;

    @FXML
    private Text PokeType;

    @FXML
    private Text Pokedex;

    @FXML
    private AnchorPane SelectedCard;

    @FXML
    private Button Trade;

    @FXML
    private Text Value;

    private static Card selectedCard;

    public static void setSelectedCard(Card card) {
        selectedCard = card;
    }


    @FXML
    public void initialize() {
        if (selectedCard != null) {
            setCardDetails(selectedCard);
        }
    }

    public void setCardDetails(Card card) {
        PokeName.setText(card.getName());
        PokeType.setText(card.getType());
        PokeRarity.setText(card.getRarity());
        Pack.setText("Pack: " + card.getPack());
        Pokedex.setText("Pokedex: " + card.getId());
        Value.setText("Value: " + card.getValue());
        EvoStage.setText("Evo Stage: " + card.getEvoStage());
        Attacks.setText("Attacks: " + String.join(", ", card.getAttacks()));
        Family.setText("Family: " + card.getFamily().toString());

        // Add the card image to the SelectedCard AnchorPane
        ImageView cardImageView = new ImageView();
        cardImageView.setFitHeight(400);  // Adjust as needed
        cardImageView.setFitWidth(350);   // Adjust as needed

        String imagePath = "/pokebros/Images/pokemon/" + card.getId() + ".png";
        try (InputStream imageStream = getClass().getResourceAsStream(imagePath)) {
            if (imageStream != null) {
                Image image = new Image(imageStream);
                cardImageView.setImage(image);
            } else {
                cardImageView.setImage(null);
            }
        } catch (IOException e) {
            e.printStackTrace();
            cardImageView.setImage(null);
        }

        SelectedCard.getChildren().clear();
        SelectedCard.getChildren().add(cardImageView);
        
    }

    @FXML
    void SwitchtoSearch(ActionEvent event) throws IOException {
        App.setRoot("tabView");
    }

    @FXML
    void SwitchtoTrade(ActionEvent event) throws IOException {
        App.setRoot("Trade");
    }

    @FXML
    void AddFavCard(ActionEvent event) {

    }
    
}

