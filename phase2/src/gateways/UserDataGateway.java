package gateways;

import entities.User;
import java.util.ArrayList;

/**
 * Gateway class.
 * Allows for communication between the UserManager and the database while still adhering to clean architecture.
 */
public interface UserDataGateway {

    /**
     * Retrieves all user data from the database and creates an instance of all of them.
     * As of now the database only stores the user's ID, username and password.
     * @return An ArrayList of all Users in the database.
     */
    ArrayList<User> fetchAllUsers();

    /**
     * Inserts a new user (from signing up) into the database.
     * @param newUser The new user to be stored.
     */
    void insertNewUser(User newUser);

    /**
     * Updates a given user's type in the database.
     * The user being updated.
     */
    void updateUserType(User u);

    /**
     * Updates a given user's password in the database.
     * @param u The user being updated.
     */
    void updateUserPassword(User u);

    /**
     * Updates a given user's list of event ID's in the database.
     * @param u The user being updated.
     */
    void updateUserEvents(User u);

    /**
     * Update a given user's list of friends in the database.
     * @param u The user being updated.
     */
    void updateUserFriends(User u);
}
