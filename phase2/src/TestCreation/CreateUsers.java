package TestCreation;

import entities.Attendee;
import entities.Organizer;
import entities.Speaker;
import entities.User;
import gateways.UserGateway;
import use_cases.UserManager;

import java.io.IOException;

public class CreateUsers {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //Users
        Attendee user1 = new Attendee("attendee1", "attendee1");
        Attendee user2 = new Attendee("attendee2", "attendee2");
        Organizer user3 = new Organizer("organizer1", "organizer1");
        Speaker user4 = new Speaker("speaker1", "speaker1");


        UserManager um = new UserManager();
        um.addUser(user1);
        um.addUser(user2);
        um.addUser(user3);
        um.addUser(user4);
        UserGateway ug = new UserGateway();
        ug.serializeUserManager("phase2/src/use_cases/UserManager.ser", um);
        UserManager um1 = ug.deserializeUserManager("phase2/src/use_cases/UserManager.ser");
        for (User e : um1.getAllUsers()) {
            System.out.println(e);
            System.out.println(e.getUsername());
            System.out.println(e.getPassword());
        }
    }
}

