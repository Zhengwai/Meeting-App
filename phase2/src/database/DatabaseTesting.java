package database;

import entities.User;
import gateways.UserDataGateway;

import java.util.ArrayList;

public class DatabaseTesting {
    public static void main(String[] args) {
        UserDataGateway mapper = new UserDataMapper();

        User newUser = new User("DatabaseTest", "12345");
        mapper.insertNewUser(newUser);

        ArrayList<User> allUsers = mapper.fetchAllUsers();

        for (User u : allUsers) {
            System.out.println(u.getUsername());
        }
    }
}
