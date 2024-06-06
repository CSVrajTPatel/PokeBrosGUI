package model;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

import java.util.Random;
import java.util.UUID;

/**
 * The User class represents a user with attributes and methods related to their activities.
 */
public class User {
    private String userName;
    private UUID uniqueIdentifier;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private ArrayList<Card> favoriteCards;
    private double currency;
    private ArrayList<Card> ownedCards;
    private Instant lastClaimedCurrencyTime;
    private ArrayList<Trade> sendingTrades = new ArrayList<Trade>();
    private ArrayList<Trade> receivingTrades = new ArrayList<Trade>();
    private ArrayList<Trade> newTrades = new ArrayList<Trade>();

    /**
     * Constructs a User object with specified attributes.
     * 
     * @param userName the username of the user.
     * @param password the password of the user.
     * @param firstName the first name of the user.
     * @param lastName the last name of the user.
     * @param email the email of the user.
     * @param favoriteCards the favorite cards of the user.
     * @param currency the currency of the user.
     * @param ownedCards the owned cards of the user.
     */
    public User(String userName, String password, String firstName, String lastName, String email, ArrayList<Card> favoriteCards, double currency, ArrayList<Card> ownedCards) {
        this.userName = userName;
        this.uniqueIdentifier = UUID.randomUUID();
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.favoriteCards = favoriteCards;
        this.currency = currency;
        this.ownedCards = ownedCards;
        this.lastClaimedCurrencyTime = Instant.now();
        this.sendingTrades = sendingTrades;
        this.receivingTrades = receivingTrades;
    }

    /**
     * Constructs a User object with specified attributes and default favorite cards and currency.
     */
    public User(String userName, String password, String firstName, String lastName, String email) {
        this(userName, password, firstName, lastName, email, new ArrayList<>(), 30, new ArrayList<>());
    }

    /**
     * Gets the username of the user.
     * 
     * @return the username.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the username of the user.
     * 
     * @param userName the new username.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets the unique identifier of the user.
     * 
     * @return the unique identifier.
     */
    public UUID getUniqueIdentifier() {
        return uniqueIdentifier;
    }

    /**
     * Gets the password of the user.
     * 
     * @return the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     * 
     * @param password the new password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the first name of the user.
     * 
     * @return the first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the user.
     * 
     * @param firstName the new first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the user.
     * 
     * @return the last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the user.
     * 
     * @param lastName the new last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the email of the user.
     * 
     * @return the email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     * 
     * @param email the new email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the favorite cards of the user.
     * 
     * @return the favorite cards.
     */
    public ArrayList<Card> getFavoriteCards() {
        return favoriteCards;
    }

    /**
     * Sets the favorite cards of the user.
     * 
     * @param favoriteCards the new favorite cards.
     */
    public void setFavoriteCards(ArrayList<Card> favoriteCards) {
        this.favoriteCards = favoriteCards;
    }

    /**
     * Adds a card to the user's favorite cards.
     * 
     * @param card the card to add.
     * @return true if the card was added, false if it was already in the list.
     */
    public boolean addFavoriteCard(Card card) {
        for (Card favCard : favoriteCards) {
            if (card == favCard) {
                return false;
            }
        }
        favoriteCards.add(card);
        return true;
    }

    /**
     * Gets the currency of the user.
     * 
     * @return the currency.
     */
    public double getCurrency() {
        return currency;
    }

    /**
     * Adds currency to the user's balance.
     * 
     * @param currency the amount to add.
     */
    public void addCurrency(double currency) {
        this.currency += currency;
    }

    /**
     * Gets the owned cards of the user.
     * 
     * @return the owned cards.
     */
    public ArrayList<Card> getOwnedCards() {
        return ownedCards;
    }

    /**
     * Gets the last time the user claimed daily currency.
     * 
     * @return the last claimed currency time.
     */
    public Instant getLastClaimedCurrencyTime() {
        return lastClaimedCurrencyTime;
    }

    /**
     * Sets the last claimed currency time for the user.
     * @param lastClaimedCurrencyTime the new last claimed currency time.
     */
    public void setLastClaimedCurrencyTime(Instant lastClaimedCurrencyTime) {
        this.lastClaimedCurrencyTime = lastClaimedCurrencyTime;
    }

    /**
     * Adds a card to the user's owned cards.
     * 
     * @param card the card to add.
     */
    public void addCardToList(Card card) {
        ownedCards.add(card);
    }

    /**
     * Removes a card from the user's owned cards.
     * 
     * @param card the card to remove.
     */
    public void removeCardFromList(Card card) {
        ownedCards.remove(card);
    }

    /**
     * Opens a pack of cards if the user has enough currency.
     * 
     * @param pack the pack number.
     * @return true if the pack was successfully opened, false otherwise.
     */
    public boolean openPack(int pack) {
        if (currency >= 10 && pack > 0 && pack < 4) {
            currency -= 10;
            Pack newPack = new Pack(pack);
            ArrayList<Card> packList = newPack.openPack();
            for (Card card : packList) {
                ownedCards.add(card);
            }
            return true;
        }
        return false;
    }

    /**
     * Claims daily currency if a day has passed since the last claim.
     * 
     * @return true if the currency was successfully claimed, false otherwise.
     */
    public boolean claimDailyCurrency() {
        Instant currentTime = Instant.now();
        Duration timeBetween = Duration.between(lastClaimedCurrencyTime, currentTime);
    
        if (timeBetween.toDays() >= 1) {
            addCurrency(25);
            setLastClaimedCurrencyTime(currentTime);
            UserList.getInstance().saveUsers(); // Save the updated user data
            return true;
        }
        return false;
    }

    /**
     * Adds a trade to the list of trades the user is sending.
     * 
     * @param trade the trade to add.
     */
    public void addSendingTrade(Trade trade) {
        sendingTrades.add(trade);
    }

    /**
     * Adds a trade to the list of trades the user is receiving.
     * 
     * @param trade the trade to add.
     */
    public void addReceivingTrade(Trade trade) {
        receivingTrades.add(trade);
    }

    /**
     * Accepts a trade for the user.
     * 
     * @param receiver the user receiving the trade.
     * @param tradeIndex which trade to accept
     * @return true if the trade was successfully accepted, false otherwise.
     */
    public boolean acceptTrade(User receiver, int tradeIndex) {
        return Trade.acceptTrade(receiver, tradeIndex);
    }

    /**
     * Rejects a trade for the user.
     * 
     * @param trade the trade to reject.
     * @return true if the trade was successfully rejected, false otherwise.
     */
    public boolean rejectTrade(Trade trade) {
        return trade.rejectTrade();
    }

    /**
     * Gets the list of trades the user is sending.
     * 
     * @return the list of sending trades.
     */
    public ArrayList<Trade> getSendingTrades() {
        return sendingTrades;
    }

    /**
     * Gets the list of trades the user is receiving.
     * 
     * @return the list of receiving trades.
     */
    public ArrayList<Trade> getReceivingTrades() {
        return receivingTrades;
    }

    /**
     * Initiates a trade with another user.
     * 
     * @param senderCards the cards offered by the sender.
     * @param receiverCard the card requested from the receiver.
     * @param comment any comment on the trade.
     * @return true if the trade was successfully initiated, false otherwise.
     */
    public boolean initiateTrade(ArrayList<Card> senderCards, Card receiverCard, String comment) {
        UserList userList = UserList.getInstance();
        for (Card card : ownedCards) {
            int count = 0;
            for (Card card2 : ownedCards) {
                if (card == card2)
                    count++;
            }

            for (Card card3 : senderCards) {
                int count2 = 0;
                for (Card card4 : senderCards) {
                    if (card3 == card4)
                        count2++;

                    if (card3 == card && count2 > count)
                        return false;
                }
            }
        }
        ArrayList<User> receivers = new ArrayList<User>();
        Random rand = new Random();
        receivers = userList.searchByCards(receiverCard);
        if (receivers.size() == 0) {
            return false;
        }
        User receiver = receivers.get(rand.nextInt(receivers.size()));
        Trade trade = new Trade(userList.searchByUserName(userName), receiver, senderCards, receiverCard, comment);
        addNewTrade(trade);
        addSendingTrade(trade);
        receiver.addReceivingTrade(trade);

        return true;
    }

    /**
     * Adds a new trade to the user's list of new trades.
     * 
     * @param trade the trade to add.
     */
    public void addNewTrade(Trade trade) {
        newTrades.add(trade);
    }

    /**
     * Clears the user's list of new trades.
     */
    public void clearNewTrades() {
        newTrades.clear();
    }

    /**
     * Gets the list of new trades for the user.
     * 
     * @return the list of new trades.
     */
    public ArrayList<Trade> getNewTrades() {
        return newTrades;
    }
}
