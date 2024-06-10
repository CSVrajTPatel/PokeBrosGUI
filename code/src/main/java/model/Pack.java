package model;
import java.util.ArrayList;
import java.util.Random;

/**
 * The Pack class represents all the packs a user can open based on pack number.
 */
public class Pack {
    
    private String name;
    private ArrayList<Card> cards;
    private int packNumber;
    private static final int CardsInPack = 7;

    /**
     * Constructs a Pack object with the specified pack number.
     * 
     * @param num the pack number.
     */
    public Pack(int num) {
        packNumber = num;
        CardList masterCardList = CardList.getInstance();
        if (num == 1) {
            name = PackName.BULBASAUR_PACK.toString();
            cards = masterCardList.searchByPack(packNumber);
        } else if (num == 2) {
            name = PackName.CHARMANDER_PACK.toString();
            cards = masterCardList.searchByPack(packNumber);
        } else if (num == 3) {
            cards = masterCardList.searchByPack(packNumber);
            name = PackName.SQUIRTILE_PACK.toString();
        }
    }

    /**
     * Opens the pack and returns a list of cards in the pack.
     * 
     * @return an ArrayList of cards in the pack.
     */
    public ArrayList<Card> openPack() {
        ArrayList<Card> newPack = new ArrayList<Card>();
        int listNum = CardsInPack;
        Random rand = new Random();
        int numRares = rand.nextInt(10); // random number between 0-9

        // Determine the number of rares in the pack
        if (numRares == 9) {
            numRares = 2;
        } else {
            numRares = 1;
        }

        int num; // temporary variable for random card index

        // Loop through the rarities and add cards to the new pack
        for (Rarity rarity : Rarity.values()) {
            num = rand.nextInt(cards.size());

            if (rarity.toString().equalsIgnoreCase("common") && numRares != 0) {
                while (numRares != 0) {
                    if (cards.get(num).getRarity().equalsIgnoreCase("common") || 
                        cards.get(num).getRarity().equalsIgnoreCase("uncommon")) {
                        num = rand.nextInt(cards.size());
                    } else {
                        newPack.add(cards.get(num));
                        numRares--;
                        listNum--;
                        num = rand.nextInt(cards.size());
                    }
                }
            }

            if (rarity.toString().equalsIgnoreCase("common")) {
                while (listNum > 0) {
                    if (cards.get(num).getRarity().equalsIgnoreCase("common") ||
                        cards.get(num).getRarity().equalsIgnoreCase("uncommon")) {
                        newPack.add(cards.get(num));
                        listNum--;
                        num = rand.nextInt(cards.size());
                    } else {
                        num = rand.nextInt(cards.size());
                    }
                }
            }

            if (numRares != 0 && 
                cards.get(num).getRarity().equalsIgnoreCase(rarity.toString())) {
                newPack.add(cards.get(num));
                listNum--;
                numRares--;
            }
        }
        return newPack;
    }

    /**
     * Returns the list of cards in the pack.
     * 
     * @return an ArrayList of cards.
     */
    public ArrayList<Card> getCardList() {
        return cards;
    }

    /**
     * Buys a pack of the specified type.
     * 
     * @param packType which pack they want to buy.
     * @return true if the pack was successfully bought.
     */

     //Why do we have this
    public boolean buyPack(int packType) {
        return true;
    }
}
