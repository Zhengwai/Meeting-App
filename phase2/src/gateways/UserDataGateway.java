package gateways;

import entities.User;
import java.util.ArrayList;
import java.util.UUID;

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
     * @param userID The ID of the user who's type will change.
     * @param newType The new type of user ("a", "o", "v", "User").
     */
    void updateUserType(UUID userID, String newType);

    /**
     * Updates a given user's password in the database.
     * @param userID The ID of the user who's password will change.
     * @param newPassword The new password for this user.
     */
    void updateUserPassword(UUID userID, String newPassword);


    /**
     * Updates a given user's list of event ID's in the database.
     * @param userID The ID of the user who's events are being updated.
     * @param newEventsList The new list of event IDs to be replaced.
     */
    void updateUserEvents(UUID userID, ArrayList<UUID> newEventsList);

    /**
     * Update a given user's list of friends in the database.
     * @param userID  The ID of the user who's events are being updated.
     * @param newFriendsList The new list of friend IDs to be replaced.
     */
    void updateUserFriends(UUID userID, ArrayList<UUID> newFriendsList);
}
