package model;
import java.util.ArrayList;

/**
 * The Trade class represents a trade between two users involving cards.
 */
public class Trade {
    private User sender;
    private User receiver;
    private ArrayList<Card> sendersCards;
    private Card receiverCard;
    private boolean isFairTrade;
    private boolean awaitingResponse;
    private boolean wasAccepted;
    private String comment;
    private double senderCoin;

    /**
     * Constructs a Trade object with all fields specified.
     * 
     * @param sender the user sending the trade.
     * @param receiver the user receiving the trade.
     * @param sendersCards the cards being offered by the sender.
     * @param receiverCard the card requested from the receiver.
     * @param isFairTrade whether the trade is fair.
     * @param awaitingResponse whether the trade is awaiting response.
     * @param wasAccepted whether the trade was accepted.
     * @param comment any comment on the trade.
     */
    public Trade(User sender, User receiver, ArrayList<Card> sendersCards, Card receiverCard, boolean isFairTrade, boolean awaitingResponse, boolean wasAccepted, String comment) {
        this.sender = sender;
        this.receiver = receiver;
        this.sendersCards = sendersCards;
        this.receiverCard = receiverCard;
        this.isFairTrade = isFairTrade;
        this.awaitingResponse = awaitingResponse;
        this.wasAccepted = wasAccepted;
        this.comment = comment;
    }

    /**
     * Constructs a Trade object with default values for fairness and response status.
     * 
     * @param sender the user sending the trade.
     * @param receiver the user receiving the trade.
     * @param sendersCards the cards being offered by the sender.
     * @param receiverCard the card requested from the receiver.
     * @param comment any comment on the trade.
     */
    public Trade(User sender, User receiver, ArrayList<Card> sendersCards, Card receiverCard, String comment) {
        this.sender = sender;
        this.receiver = receiver;
        this.sendersCards = sendersCards;
        this.receiverCard = receiverCard;
        this.comment = comment;
        this.isFairTrade = isFairTrade();
        this.awaitingResponse = true;
    }

    /**
     * Gets the sender of the trade.
     * 
     * @return the sender user.
     */
    public User getSender() {
        return sender;
    }

    /**
     * Gets the receiver of the trade.
     * 
     * @return the receiver user.
     */
    public User getReceiver() {
        return receiver;
    }

    /**
     * Determines if the trade is fair based on card values.
     * 
     * @return true if the trade is fair, false otherwise.
     */
    public boolean isFairTrade() {
        double senderCardValue = 0;
        for (int i = 0; i < sendersCards.size(); i++) {
            senderCardValue += sendersCards.get(i).getValue();
        }
        if (receiverCard.getValue() / senderCardValue >= 0.9 && receiverCard.getValue() / senderCardValue <= 1.1) {
            isFairTrade = true;
            return isFairTrade;
        }
        isFairTrade = false;
        return isFairTrade;
    }

    /**
     * Checks if the trade is awaiting a response.
     * 
     * @return true if awaiting response, false otherwise.
     */
    public boolean isAwaitingResponse() {
        return awaitingResponse;
    }

    /**
     * Sets the awaiting response status of the trade.
     * 
     * @param awaitingResponse the response status to set.
     */
    public void setAwaitingResponse(boolean awaitingResponse) {
        this.awaitingResponse = awaitingResponse;
    }

    /**
     * Accepts a trade for the specified receiver and trade index.
     * 
     * @param receiver the user receiving the trade.
     * @param tradeIndex the index of the trade to accept as given in the ArrayList.
     * @return true if the trade was successfully accepted, false otherwise.
     */
    public static boolean acceptTrade(User receiver, int tradeIndex) {
        ArrayList<Trade> receivingTrades = receiver.getReceivingTrades();
        if (tradeIndex < 0 || tradeIndex >= receivingTrades.size()) {
            return false;
        }
        Trade tradeToAccept = receivingTrades.get(tradeIndex);
        if (!tradeToAccept.awaitingResponse) {
            return false;
        }
        tradeToAccept.wasAccepted = true;
        tradeToAccept.awaitingResponse = false;
        
        tradeToAccept.sender.addCardToList(tradeToAccept.receiverCard);
        tradeToAccept.receiver.removeCardFromList(tradeToAccept.receiverCard);

        for (Card card : tradeToAccept.sendersCards) {
            tradeToAccept.receiver.addCardToList(card);
            tradeToAccept.sender.removeCardFromList(card);
        }
        
        DataWriter.updateAcceptedTrade(tradeToAccept);
        return true;
    }

    /**
     * Rejects the trade.
     * 
     * @return true if the trade was successfully rejected, false otherwise.
     */
    public boolean rejectTrade() {
        if (awaitingResponse) {
            awaitingResponse = false;
            return true;
        }
        return false;
    }

    /**
     * Gets the comment on the trade.
     * 
     * @return the trade comment.
     */
    public String getComment() {
        return comment;
    }

    /**
     * Checks if the trade was accepted.
     * 
     * @return true if the trade was accepted, false otherwise.
     */
    public boolean wasAccepted() {
        return wasAccepted;
    }

    /**
     * Gets the cards offered by the sender.
     * 
     * @return an ArrayList of cards offered.
     */
    public ArrayList<Card> getCardsOffered() {
        return sendersCards;
    }

    /**
     * Gets the card requested by the receiver.
     * 
     * @return the card requested by the receiver.
     */
    public Card getReceiverCard() {
        return receiverCard;
    }

    /**
     * Gets the sender's coin value.
     * 
     * @return the sender's coin value.
     */
    public double getSenderCoin() {
        return senderCoin;
    }
}
