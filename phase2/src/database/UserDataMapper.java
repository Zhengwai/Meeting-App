package database;

import entities.User;
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
            while(rs.next()) {
                User u = new User(rs.getString("username"), rs.getString("password"));
                u.setId(UUID.fromString(rs.getString("uuid")));
                out.add(u);
            }
            return out;
        } catch (SQLException e) {
            System.out.println("Something went wrong with the users ResultSet");
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
            db.insertUser(newUser.getID(), newUser.getUsername(), newUser.getPassword());
        } catch (SQLException e) {
            System.out.println("Something went wrong trying to insert a new user");
            e.printStackTrace();
        }
    }

    @Override
    public void saveAllUsers(ArrayList<User> users) {
        //TODO: Need database to handle data updates.
        // Right now we only have reading (SELECT) and
        // writing a new user (INSERT).
    }
}
