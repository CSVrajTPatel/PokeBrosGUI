package model;
import java.util.ArrayList;

/**
 * Represents a list of cards.
 */
public class CardList {
    private static CardList masterList;
    private static ArrayList<Card> cardList;

    /**
     * Initializes the card list from the DataLoader.
     */
    private CardList() {
        cardList = DataLoader.loadCards();
    }

    /**
     * Gets the single instance of the CardList.
     * 
     * @return The single instance of the CardList.
     */
    public static CardList getInstance() {
        if (masterList == null) {
            masterList = new CardList();
        }
        return masterList;
    }

    /**
     * Gets the list of cards.
     * 
     * @return The list of cards.
     */
    public ArrayList<Card> getCardList() {
        return cardList;
    }

    /**
     * Searches for a card by its name.
     * 
     * @param name The name of the card.
     * @return The card with the specified name, or null if not found.
     */
    public ArrayList<Card> searchByName(String filter) {
        ArrayList<Card> result = new ArrayList<>();
        for (Card card : cardList) {
            if (card.getName().contains(filter)) {
                result.add(card);
            }
        }
        return result;
    }

    /**
     * Searches for cards by their name.
     * 
     * @param name The name of the card.
     * @param cards The list of cards to search.
     * @return A list of cards with the specified name.
     */
    public ArrayList<Card> searchByName(String name, ArrayList<Card> cards) {
        ArrayList<Card> result = new ArrayList<>();
        for (Card card : cards) {
            if (card.getName().contains(name)) {
                result.add(card);
            }
        }
        return result;
    }

    /**
     * Searches for cards by their type.

     * @param type The type of the card.
     * @return A list of cards with the specified type.
     */
    public ArrayList<Card> searchByType(String type) {
        ArrayList<Card> result = new ArrayList<>();
        for (Card card : cardList) {
            if (card.getType().equalsIgnoreCase(type)) {
                result.add(card);
            }
        }
        return result;
    }

    /**
     * Searches for cards by their type in a given list of cards. REFINED SEARCH
     * @param type The type of the card.
     * @param cards The list of cards to search.
     * @return A list of cards with the specified type.
     */
    public ArrayList<Card> searchByType(String type, ArrayList<Card> cards) {
        ArrayList<Card> result = new ArrayList<>();
        for (Card card : cards) {
            if (card.getType().equalsIgnoreCase(type)) {
                result.add(card);
            }
        }
        return result;
    }

    /**
     * Searches for cards by their rarity.
     * 
     * @param rarity The rarity of the card.
     * @return A list of cards with the specified rarity.
     */
    public ArrayList<Card> searchByRarity(String rarity) {
        ArrayList<Card> result = new ArrayList<>();
        for (Card card : cardList) {
            if (card.getRarity().equalsIgnoreCase(rarity)) {
                result.add(card);
            }
        }
        return result;
    }

    /**
     * Searches for cards by their rarity in a given list of cards. REFINED SEARCH.
     * 
     * @param rarity The rarity of the card.
     * @param cards The list of cards to search.
     * @return A list of cards with the specified rarity.
     */
    public ArrayList<Card> searchByRarity(String rarity, ArrayList<Card> cards) {
        ArrayList<Card> result = new ArrayList<>();
        for (Card card : cards) {
            if (card.getRarity().equalsIgnoreCase(rarity)) {
                result.add(card);
            }
        }
        return result;
    }

    /**
     * Searches for a card by its ID.
     * 
     * @param id The ID of the card.
     * @return The card with the specified ID, or null if not found.
     */
    public Card searchById(int id) {
        for (Card card : cardList) {
            if (card.getId() == id) {
                return card;
            }
        }
        return null;
    }

    /**
     * Searches for a card by its ID in a given list of cards. REFINED SEARCH
     * 
     * @param id The ID of the card.
     * @param cards The list of cards to search.
     * @return The card with the specified ID, or null if not found.
     */
    public Card searchById(int id, ArrayList<Card> cards) {
        for (Card card : cards) {
            if (card.getId() == id) {
                return card;
            }
        }
        return null;
    }

    /**
     * Searches for cards by their pack number.
     * 
     * @param pack The pack number of the card.
     * @return A list of cards with the specified pack number.
     */
    public ArrayList<Card> searchByPack(int pack) {
        ArrayList<Card> result = new ArrayList<>();
        for (Card card : cardList) {
            if (card.getPack() == pack) {
                result.add(card);
            }
        }
        return result;
    }

    /**
     * Searches for cards by their pack number in a given list of cards. REFINED SEARCH
     * 
     * @param pack The pack number of the card.
     * @param cards The list of cards to search.
     * @return A list of cards with the specified pack number.
     */
    public ArrayList<Card> searchByPack(int pack, ArrayList<Card> cards) {
        ArrayList<Card> result = new ArrayList<>();
        for (Card card : cards) {
            if (card.getPack() == pack) {
                result.add(card);
            }
        }
        return result;
    }
}
