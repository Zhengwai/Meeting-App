package database;

import entities.Attendee;
import entities.User;
import gateways.UserDataGateway;

import java.util.ArrayList;
import java.util.UUID;

public class DatabaseTesting {
    public static void main(String[] args) {
        UserDataGateway mapper = new UserDataMapper();

        //User newUser = new Attendee("AttendeeTest", "12345");
        //mapper.insertNewUser(newUser);

        //UUID dummyID = UUID.fromString("f346e3ea-ff5b-4ebe-97dc-a0fba07b9988");
        //mapper.updateUserType(dummyID, "o");
        //mapper.updateUserPassword(dummyID, "updatePassword");

        ArrayList<User> allUsers = mapper.fetchAllUsers();
        for (User u : allUsers) {
            System.out.println(u.getUsername() + " " + u.getType());
        }
    }
}
