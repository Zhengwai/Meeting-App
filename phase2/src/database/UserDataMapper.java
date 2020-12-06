package database;

import entities.*;
import gateways.UserDataGateway;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class UserDataMapper implements UserDataGateway {
    private Database db;

    public UserDataMapper() {
        db = new Database();
    }

    /**
     * Retrieves all user data from the database and creates an instance of all of them.
     * As of now the database only stores the user's ID, username and password.
     * @return An ArrayList of all Users in the database.
     */
    @Override
    public ArrayList<User> fetchAllUsers() {
        try {
            ArrayList<User> out = new ArrayList<>();
            ResultSet rs = db.getAllUsers();

            // Another design pattern: Iterator
            while(rs.next()) {
                User u = null;

                // Instantiate by user's type
                switch (rs.getString("type")) {
                    case "a":
                        u = new Attendee(rs.getString("username"), rs.getString("password"));
                        break;
                    case "o":
                        u = new Organizer(rs.getString("username"), rs.getString("password"));
                        break;
                    case "s":
                        u = new Speaker(rs.getString("username"), rs.getString("password"));
                        break;
                    case "v":
                        u = new VIP(rs.getString("username"), rs.getString("password"));
                        break;
                    case "User":
                        u = new User(rs.getString("username"), rs.getString("password"));
                        break;
                }
                assert u != null;

                // Final data mapping
                u.setId(UUID.fromString(rs.getString("uuid")));

                String rawEvents = (String) rs.getObject("events");
                if (rawEvents != null) {
                    rawEvents = rawEvents.substring(1, rawEvents.length() - 1); // Remove the "[" and "]" from string
                    String[] eventList = rawEvents.split(", ");

                    for (String s : eventList) {
                        u.addEvent(UUID.fromString(s));
                    }
                }

                String rawFriends = (String) rs.getObject("friends");
                if (rawFriends != null) {
                    rawFriends = rawFriends.substring(1, rawFriends.length() - 1); // Remove the "[" and "]" from string
                    String[] friendList = rawFriends.split(", ");

                    for (String s: friendList) {
                        u.addFriend(UUID.fromString(s));
                    }
                }

                out.add(u);
            }

            return out;
        } catch (SQLException e) {
            System.out.println("Something went wrong with the users ResultSet.");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Inserts a new user (from signing up) into the database.
     * @param newUser The new user to be stored.
     */
    @Override
    public void insertNewUser(User newUser) {
        try {
            db.insertUser(newUser.getID(), newUser.getUsername(), newUser.getPassword(), newUser.getType());
        } catch (SQLException e) {
            System.out.println("Something went wrong trying to insert a new user.");
            e.printStackTrace();
        }
    }

    /**
     * Updates a given user's type in the database.
     * @param userID The ID of the user who's type will change.
     * @param newType The new type of user ("a", "o", "v", "User").
     */
    @Override
    public void updateUserType(UUID userID, String newType) {
        try {
            db.updateUserType(userID, newType);
        } catch (SQLException e) {
            System.out.println("Something went wrong trying to update this user's type.");
        }
    }

    /**
     * Updates a given user's password in the database.
     * @param userID The ID of the user who's password will change.
     * @param newPassword The new password for this user.
     */
    @Override
    public void updateUserPassword(UUID userID, String newPassword) {
        try {
            db.updateUserPassword(userID, newPassword);
        } catch (SQLException e) {
            System.out.println("Something went wrong trying to update this user's password.");
        }
    }

    /**
     * Updates a given user's list of event ID's in the database.
     * @param userID The ID of the user who's events are being updated.
     * @param newEventsList The new list of event IDs to be replaced.
     */
    @Override
    public void updateUserEvents(UUID userID, ArrayList<UUID> newEventsList) {
        try {
            db.updateUserEvents(userID, newEventsList);
        } catch (SQLException e) {
            System.out.println("Something went wrong trying to update this user's events.");
        }
    }

    /**
     * Update a given user's list of friends in the database.
     * @param userID  The ID of the user who's events are being updated.
     * @param newFriendsList The new list of friend IDs to be replaced.
     */
    @Override
    public void updateUserFriends(UUID userID, ArrayList<UUID> newFriendsList) {
        try {
            db.updateUserFriends(userID, newFriendsList);
        } catch (SQLException e) {
            System.out.println("Something went wrong trying to update this user's friends.");
        }
    }
}
