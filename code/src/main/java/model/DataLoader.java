package model;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * The DataLoader class provides methods to load the data from the Cards,Users, and trades JSON files.
 */
public class DataLoader {
    private static final String CARDS_FILE_PATH = "json/cards.json";
    private static final String USERS_FILE_PATH = "json/users.json";
    private static final String TRADES_FILE_PATH = "json/trades.json";

    /**
     * Loads cards from the Card.JSON file.
     * 
     * @return an ArrayList of Card objects.
     */
    public static ArrayList<Card> loadCards() {
        ArrayList<Card> cards = new ArrayList<>();
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(CARDS_FILE_PATH)) {
            JSONArray cardsArray = (JSONArray) parser.parse(reader);
            for (Object obj : cardsArray) {
                JSONObject cardObject = (JSONObject) obj;
                cards.add(parseCard(cardObject));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return cards;
    }

    /**
     * Loads users from the Users.JSON file.
     * 
     * @return an ArrayList of User objects.
     */
    public static ArrayList<User> loadUsers() {
        ArrayList<User> users = new ArrayList<>();
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(USERS_FILE_PATH)) {
            JSONArray usersArray = (JSONArray) parser.parse(reader);
            for (Object obj : usersArray) {
                JSONObject userObject = (JSONObject) obj;
                users.add(parseUser(userObject));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * Loads trades from the trades.JSON file.
     * 
     * @return an ArrayList of Trade objects.
     */
    public static ArrayList<Trade> loadTrades() {
        ArrayList<Trade> trades = new ArrayList<>();
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(TRADES_FILE_PATH)) {
            JSONArray tradesArray = (JSONArray) parser.parse(reader);
            for (Object obj : tradesArray) {
                JSONObject tradeObject = (JSONObject) obj;
                trades.add(parseTrade(tradeObject));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return trades;
    }

    /**
     * Parses a JSONObject into a Card object.
     * 
     * @param cardObject the JSONObject to parse.
     * @return a Card object.
     */
    private static Card parseCard(JSONObject cardObject) {
        int id = getIntValue(cardObject, "id");
        String name = getStringValue(cardObject, "name");
        String type = getStringValue(cardObject, "type");
        String rarity = getStringValue(cardObject, "rarity");
        int pack = getIntValue(cardObject, "pack");
        int hp = getIntValue(cardObject, "hp");
        double value = getDoubleValue(cardObject, "value");
        int evoStage = getIntValue(cardObject, "evoStage");

        ArrayList<Integer> familyCards = getIntegerList(cardObject, "family");
        ArrayList<String> attacks = getStringList(cardObject, "attacks");

        return new Card(id, name, type, rarity, pack, hp, value, evoStage, familyCards, attacks);
    }

    /**
     * Gets an integer value from a JSONObject.
     * 
     * @param jsonObject the JSONObject.
     * @param key the key of the value.
     * @return the integer value.
     */
    private static int getIntValue(JSONObject jsonObject, String key) {
        Object value = jsonObject.get(key);
        if (value instanceof Long) {
            return ((Long) value).intValue();
        }
        return 0;
    }

    /**
     * Gets a double value from a JSONObject.
     * 
     * @param jsonObject the JSONObject.
     * @param key value of the double.
     * @return the double value.
     */
    private static double getDoubleValue(JSONObject jsonObject, String key) {
        Object value = jsonObject.get(key);
        if (value instanceof Long) {
            return ((Long) value).doubleValue();
        } else if (value instanceof Double) {
            return (Double) value;
        }
        return 0.0;
    }

    /**
     * Gets a string value from a JSONObject.
     * 
     * @param jsonObject the JSONObject.
     * @param key the key of the value.
     * @return the string value.
     */
    private static String getStringValue(JSONObject jsonObject, String key) {
        Object value = jsonObject.get(key);
        return value != null ? value.toString() : "";
    }

    /**
     * Gets an ArrayList of integers from a JSONObject.
     * 
     * @param jsonObject the JSONObject.
     * @param key the key of the value.
     * @return an ArrayList of integers.
     */
    private static ArrayList<Integer> getIntegerList(JSONObject jsonObject, String key) {
        ArrayList<Integer> list = new ArrayList<>();
        Object value = jsonObject.get(key);
        if (value instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray) value;
            for (Object obj : jsonArray) {
                if (obj instanceof Long) {
                    list.add(((Long) obj).intValue());
                }
            }
        } else if (value instanceof Long) {
            list.add(((Long) value).intValue());
        }
        return list;
    }

    /**
     * Gets an ArrayList of strings from a JSONObject.
     * 
     * @param jsonObject the JSONObject.
     * @param key the key of the value.
     * @return an ArrayList of strings.
     */
    private static ArrayList<String> getStringList(JSONObject jsonObject, String key) {
        ArrayList<String> list = new ArrayList<>();
        Object value = jsonObject.get(key);
        if (value instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray) value;
            for (Object obj : jsonArray) {
                if (obj instanceof String) {
                    list.add((String) obj);
                }
            }
        } else if (value instanceof String) {
            list.add((String) value);
        }
        return list;
    }

    /**
     * Parses a JSONObject into a User object.
     * 
     * @param userObject the JSONObject to parse.
     * @return a User object.
     */
    private static User parseUser(JSONObject userObject) {
        String userName = getStringValue(userObject, "userName");
        String uniqueIdentifier = getStringValue(userObject, "uniqueIdentifier");
        String password = getStringValue(userObject, "password");
        String firstName = getStringValue(userObject, "firstName");
        String lastName = getStringValue(userObject, "lastName");
        String email = getStringValue(userObject, "email");
        double currency = getDoubleValue(userObject, "currency");

        ArrayList<Integer> favoriteCardsTemp = getIntegerList(userObject, "favoriteCards");
        ArrayList<Card> favoriteCards = new ArrayList<>();
        CardList masterList = CardList.getInstance();
        for (Integer temp : favoriteCardsTemp) {
            favoriteCards.add(masterList.searchById(temp));
        }

        ArrayList<Integer> ownedCardsTemp = getIntegerList(userObject, "ownedCards");
        ArrayList<Card> ownedCards = new ArrayList<>();
        for (Integer temp : ownedCardsTemp) {
            ownedCards.add(masterList.searchById(temp));
        }

        Instant lastClaimedCurrencyTime = Instant.parse(getStringValue(userObject, "lastClaimedCurrencyTime"));

        User user = new User(userName, password, firstName, lastName, email, favoriteCards, currency, ownedCards);
        user.setLastClaimedCurrencyTime(lastClaimedCurrencyTime);

        return user;
    }

    /**
     * Parses a JSONObject into a Trade object.
     * 
     * @param tradeObject the JSONObject to parse.
     * @return a Trade object.
     */
    private static Trade parseTrade(JSONObject tradeObject) {
        UserList userList = UserList.getInstance();
        CardList masterList = CardList.getInstance();
        String receiverUserName = getStringValue(tradeObject, "receiverUserName");
        User receiver = userList.searchByUserName(receiverUserName);

        String senderUserName = getStringValue(tradeObject, "senderUserName");
        User sender = userList.searchByUserName(senderUserName); 

        ArrayList<Integer> cardsIds = getIntegerList(tradeObject, "cardsOffered");
        ArrayList<Card> cardsOffered = new ArrayList<Card>();
        for (int num : cardsIds) {
            cardsOffered.add(masterList.searchById(num));
        }
        int cardRequested = getIntValue(tradeObject, "cardsRequested");
        Card realCard = masterList.searchById(cardRequested);
        boolean isFairTrade = (Boolean) tradeObject.get("isFairTrade");
        boolean awaitingResponse = (Boolean) tradeObject.get("awaitingResponse");
        boolean wasAccepted = (Boolean) tradeObject.get("wasAccepted");
        String comment = getStringValue(tradeObject, "comment");

        Trade trade = new Trade(sender, receiver, cardsOffered, realCard, isFairTrade, awaitingResponse, wasAccepted, comment);
        if (sender != null)
            sender.addReceivingTrade(trade);

        if (receiver != null)
            receiver.addReceivingTrade(trade);

        return trade;
    }
}
