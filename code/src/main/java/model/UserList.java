package model;
import java.util.ArrayList;

/**
 * The UserList class manages the list of users.
 */
public class UserList {
    private static UserList masterList;
    private static ArrayList<User> userList;

    /**
     * Private constructor to load users.
     */
    private UserList() {
        userList = DataLoader.loadUsers();
    }

    /**
     * Gets the single instance of UserList.
     * 
     * @return the instance of UserList.
     */
    public static UserList getInstance() {
        if (masterList == null) {
            masterList = new UserList();
        }
        return masterList;
    }

    /**
     * Adds a user to the list, if it passes all the current tests
     * 
     * @return true if user added, false otherwise.
     */
    public boolean addUserToList(String userName, String password, String firstName, String lastName, String email) {
        for (User user : userList) {
            if (user.getUserName().equalsIgnoreCase(userName) || userName.equalsIgnoreCase("") || userName.contains(" ") || userName == null) {
                return false;
            }
            if (!(email.contains("@") && email.contains("."))) {
                return false;
            }
        }
        User newUser = new User(userName, password, firstName, lastName, email);
        userList.add(newUser);
        return true;
    }

    /**
     * Logs in a user.
     * 
     * @param userName the username.
     * @param password the password.
     * @return the User object if login successful, null otherwise.
     */
    public User loginUser(String userName, String password) {
        getInstance();
        for (User user : userList) {
            if (user.getUserName().equalsIgnoreCase(userName) && user.getPassword().equals(password)) {
                DataLoader.loadTrades();
                return user;
            }
        }
        return null;
    }

    /**
     * Removes a user from the list.
     * 
     * @param username the username.
     * @return true if user removed, false otherwise.
     */
    public boolean removeUserFromList(String username) {
        for (User user : userList) {
            if (user.getUserName().equals(username)) {
                userList.remove(user);
                DataWriter.updateUsers(userList);
                return true;
            }
        }
        return false;
    }

    /**
     * Searches for a user by username.
     * 
     * @param username the username.
     * @return the User object if found, null otherwise.
     */
    public User searchByUserName(String username) {
        for (User user : userList) {
            if (user.getUserName().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Searches for users by card.
     * 
     * @param card the card.
     * @return a list of users who own the card.
     */
    public ArrayList<User> searchByCards(Card card) {
        ArrayList<User> returnList = new ArrayList<User>();
        for (User user : userList) {
            ArrayList<Card> userCards = user.getOwnedCards();
            for (Card userCard : userCards) {
                if (userCard == card) {
                    returnList.add(user);
                    break;
                }
            }
        }
        return returnList;
    }

    /**
     * Logs off a user, updates trades, updates users, and removes all the nonpendingtrades.
     * 
     * @param username the username.
     */
    public void logOffUser(String username) {
        User user = searchByUserName(username);
        if (user != null) {
            DataWriter.updateUsers(userList);
            DataWriter.updateTrades(user.getSendingTrades());
            DataWriter.removeNonPendingTrades();
        }
    }

    /**
     * Saves the list of users.
     */
    public void saveUsers() {
        DataWriter.updateUsers(userList);
    }
}
