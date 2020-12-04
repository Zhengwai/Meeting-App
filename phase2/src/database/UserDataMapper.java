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
                u.setId(UUID.fromString(rs.getString("uuid")));
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

    @Override
    public void updateUserType(UUID userID, String newType) {
        try {
            db.updateUserType(userID, newType);
        } catch (SQLException e) {
            System.out.println("Something went wrong trying to update that user's type.");
        }
    }

    @Override
    public void updateUserPassword(UUID userID, String newPassword) {
        try {
            db.updateUserPassword(userID, newPassword);
        } catch (SQLException e) {
            System.out.println("Something went wrong trying to update that user's password.");
        }
    }
}
