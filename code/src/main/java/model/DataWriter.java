package model;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * The DataWriter class provides methods to update JSON files with user and trade data.
 */
public class DataWriter {
    private static final String USERS_FILE_PATH = "code/src/main/java/data/users.json";
    private static final String TRADES_FILE_PATH = "code/src/main/java/data/trades.json";


    /**
     * Updates the users JSON file with the given list of users.
     */
    public static void updateUsers(ArrayList<User> users) {
        JSONArray usersArray = new JSONArray();
        for (User user : users) {
            usersArray.add(userToJson(user));
        }
        writeJsonArrayToFile(usersArray, USERS_FILE_PATH);
    }

    /**
     * Converts a User object to a JSONObject.
     */
    private static JSONObject userToJson(User user) {
        JSONObject userObject = new JSONObject();
        userObject.put("userName", user.getUserName());
        userObject.put("uniqueIdentifier", user.getUniqueIdentifier().toString());
        userObject.put("password", user.getPassword());
        userObject.put("firstName", user.getFirstName());
        userObject.put("lastName", user.getLastName());
        userObject.put("email", user.getEmail());
        userObject.put("currency", user.getCurrency());
        userObject.put("lastClaimedCurrencyTime", user.getLastClaimedCurrencyTime().toString());

        JSONArray favoriteCardsArray = new JSONArray();
        for (Card card : user.getFavoriteCards()) {
            favoriteCardsArray.add(card.getId());
        }
        userObject.put("favoriteCards", favoriteCardsArray);

        JSONArray ownedCardsArray = new JSONArray();
        for (Card card : user.getOwnedCards()) {
            ownedCardsArray.add(card.getId());
        }
        userObject.put("ownedCards", ownedCardsArray);

        return userObject;
    }

    /**
     * Removes a user from the JSON file based on the userName.
     */
    private static void removeUser(String targetUserName) {
        JSONArray existingUsersArray = readJsonArrayFromFile(USERS_FILE_PATH);
        Iterator<JSONObject> iterator = existingUsersArray.iterator();
        while (iterator.hasNext()) {
            JSONObject userJson = iterator.next();
            if (getStringValue(userJson, "userName").equals(targetUserName)) {
                iterator.remove();
            }
        }
        writeJsonArrayToFile(existingUsersArray, USERS_FILE_PATH);
    }

    /**
     * Gets a string value from a JSONObject.
     */
    private static String getStringValue(JSONObject jsonObject, String key) {
        Object value = jsonObject.get(key);
        return value != null ? value.toString() : "";
    }

    /**
     * Updates the trades JSON file with the given list of trades.
     */
    public static void updateTrades(ArrayList<Trade> trades) {
        JSONArray tradesArray = readJsonArrayFromFile(TRADES_FILE_PATH);
        if (tradesArray == null) {
            tradesArray = new JSONArray();
        }

        for (Trade trade : trades) {
            tradesArray.add(tradeToJson(trade));
        }
        writeJsonArrayToFile(tradesArray, TRADES_FILE_PATH);
    }

    /**
     * Converts a Trade object to a JSONObject.
     */
    private static JSONObject tradeToJson(Trade trade) {
        JSONObject tradeObject = new JSONObject();
        tradeObject.put("receiverUserName", trade.getReceiver().getUserName());
        tradeObject.put("senderUserName", trade.getSender().getUserName());

        JSONArray cardsOfferedArray = new JSONArray();
        for (Card card : trade.getCardsOffered()) {
            cardsOfferedArray.add(card.getId());
        }
        tradeObject.put("cardsOffered", cardsOfferedArray);

        tradeObject.put("cardsRequested", trade.getReceiverCard().getId());

        tradeObject.put("isFairTrade", trade.isFairTrade());
        tradeObject.put("awaitingResponse", trade.isAwaitingResponse());
        tradeObject.put("wasAccepted", trade.wasAccepted());
        tradeObject.put("comment", trade.getComment());

        return tradeObject;
    }

    /**
     * Updates the JSON file with the accepted trade.
     */
    public static void updateAcceptedTrade(Trade acceptedTrade) {
        JSONArray tradesArray = readJsonArrayFromFile(TRADES_FILE_PATH);
        for (int i = 0; i < tradesArray.size(); i++) {
            JSONObject tradeJson = (JSONObject) tradesArray.get(i);
            if (tradeJson.get("senderUserName").equals(acceptedTrade.getSender().getUserName()) &&
                tradeJson.get("receiverUserName").equals(acceptedTrade.getReceiver().getUserName())) {
                tradeJson.put("awaitingResponse", false);
                tradeJson.put("wasAccepted", true);
                tradesArray.set(i, tradeJson);
                break;
            }
        }
        writeJsonArrayToFile(tradesArray, TRADES_FILE_PATH);
    }

    /**
     * Removes non-pending trades from the JSON file.
     */
    public static void removeNonPendingTrades() {
        JSONArray tradesArray = readJsonArrayFromFile(TRADES_FILE_PATH);
        Iterator<JSONObject> iterator = tradesArray.iterator();
        while (iterator.hasNext()) {
            JSONObject tradeJson = iterator.next();
            if (!(Boolean) tradeJson.get("awaitingResponse")) {
                iterator.remove();
            }
        }
        writeJsonArrayToFile(tradesArray, TRADES_FILE_PATH);
    }

    /**
     * Reads a JSONArray from a file.
     */
    private static JSONArray readJsonArrayFromFile(String filePath) {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(filePath)) {
            Object obj = jsonParser.parse(reader);
            return (JSONArray) obj;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return new JSONArray();  // Return an empty array in case of failure to avoid nulls
        }
    }

    /**
     * Writes a JSONArray to a file.
     */
    private static void writeJsonArrayToFile(JSONArray jsonArray, String filePath) {
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(jsonArray.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
