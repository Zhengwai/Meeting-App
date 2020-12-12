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

    @Override
    public ArrayList<User> fetchAllUsers() {
        try {
            ArrayList<User> out = new ArrayList<>();
            ResultSet rs = db.getAllFromTable("users");

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

                String[] eventsList = db.parseArrayList((String) rs.getObject("events"));
                if (eventsList != null) {
                    for (String s : eventsList) {
                        u.addEvent(UUID.fromString(s));
                    }
                }

                String[] friendsList = db.parseArrayList((String) rs.getObject("friends"));
                if (friendsList != null) {
                    for (String s: friendsList) {
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
    public void updateUserType(User u) {
        try {
            db.updateTableRowValue("users", "type", u.getID(), u.getType());
        } catch (SQLException e) {
            System.out.println("Something went wrong trying to update this user's type.");
        }
    }

    @Override
    public void updateUserPassword(User u) {
        try {
            db.updateTableRowValue("users", "password", u.getID(), u.getPassword());
        } catch (SQLException e) {
            System.out.println("Something went wrong trying to update this user's password.");
        }
    }

    @Override
    public void updateUserEvents(User u) {
        try {
            db.updateTableRowValue("users", "events", u.getID(), u.getEnrolledEvents());
        } catch (SQLException e) {
            System.out.println("Something went wrong trying to update this user's events.");
        }
    }

    @Override
    public void updateUserFriends(User u) {
        try {
            db.updateTableRowValue("users", "events", u.getID(), u.getEnrolledEvents());
        } catch (SQLException e) {
            System.out.println("Something went wrong trying to update this user's friends.");
        }
    }
}
