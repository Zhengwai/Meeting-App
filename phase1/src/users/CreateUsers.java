package users;

import ScheduleSystem.Event;
import ScheduleSystem.Serialization;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CreateUsers {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //Users
        Attendee user1 = new Attendee("attendee", "attendee");
        Attendee user2 = new Attendee("attendee2", "attendee2");
        Organizer user3 = new Organizer("organizer1", "organizer1");
        Speaker user4 = new Speaker("speaker1", "speaker1");


        ArrayList<User> allUsers = new ArrayList<User>();
        allUsers.add(user1);
        allUsers.add(user2);
        allUsers.add(user3);
        allUsers.add(user4);
        UserGateway ug = new UserGateway();
        ug.serializeUsers("phase1/Users.ser", allUsers);
        ArrayList<User> users = ug.deserializeUsers("phase1/Users.ser");
        for (User e : users) {
            System.out.println(e);
            System.out.println(e.getUsername());
            System.out.println(e.getPassword());
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("EnterUserName:");
        String user = sc.nextLine();
        System.out.println("EnterPassWord");
        String pass = sc.nextLine();
        System.out.println(users.get(0).getUsername().equals(user));
        System.out.println(users.get(0).getPassword().equals(pass));

    }
}
