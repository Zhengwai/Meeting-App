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
        UUID dummyID = UUID.fromString("8cd3ac39-6a91-4d5a-be96-e4bd2b5596e1");
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
