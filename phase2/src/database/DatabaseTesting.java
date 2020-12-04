package database;

import entities.Attendee;
import entities.User;
import gateways.UserDataGateway;

import java.util.ArrayList;
import java.util.UUID;

public class DatabaseTesting {
    public static void main(String[] args) {
        UserDataGateway mapper = new UserDataMapper();


        // Setup stuff (creating a dummy user and messing around with it)
        User newUser = new Attendee("test", "test");
        mapper.insertNewUser(newUser);

        UUID dummyID = newUser.getID();
        UUID randomID1 = UUID.randomUUID();
        UUID randomID2 = UUID.randomUUID();
        ArrayList<UUID> test = new ArrayList<UUID>();

        test.add(randomID1);
        test.add(randomID2);

        mapper.updateUserEvents(dummyID, test);

        ArrayList<User> allUsers = mapper.fetchAllUsers();
        for (User u : allUsers) {
            System.out.println(u.getUsername() + " " + u.getType());
        }
    }
}
