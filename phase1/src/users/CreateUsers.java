package users;

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
        ug.serializeUserManager("um.ser", um);
        UserManager um1 = ug.deserializeUserManager("um.ser");
        for (User e : um1.getAllUsers()) {
            System.out.println(e);
            System.out.println(e.getUsername());
            System.out.println(e.getPassword());
        }
    }
}

