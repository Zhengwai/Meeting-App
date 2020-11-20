package message_system;


import ScheduleSystem.EventManager;
import users.User;
import users.UserGateway;
import users.UserManager;
import ScheduleSystem.Event;
import users.Speaker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.UUID;

public class SpeakerMessageController extends AttendeeMessageController {
    private ConversationManager cm;
    private Speaker user;

    public SpeakerMessageController(User inpUser, UserManager um, EventManager em) {
        super(inpUser, um, em);
        user = (Speaker) inpUser;
    }

    public void run() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            String input = "";
            while (!input.equals("exit")) {
                System.out.println("Please Enter Corresponding Choice: \n " +
                        "1. Add Friend \n " +
                        "2. Message Existing Conversation \n" +
                        "3. New Message to all attendees of a talk \n" +
                        "exit to exit this Controller");
                input = br.readLine();

                switch(input) {
                    case "1":
                        System.out.println("Enter the username of the person you want to add");
                        input = br.readLine();
                        handleAddFriend(input);

                    case "2":
                        ArrayList<Conversation> conversations = cm.getConversations(this.user.getConversations());
                        System.out.println(mp.promptMainScreen(conversations));
                        System.out.println("Enter the number of the conversation to open:");
                        input = br.readLine();
                        handleConversations(input, conversations);

                    case "3":
                        handleMessageAllAttendees();

                    default:
                        if (!input.equals("exit")) {
                            System.out.println("Chose invalid option");
                        }
                }

            }
            serializeCM();
        } catch (IOException e) {
            System.out.println("Failed to read input");
            e.printStackTrace();
        }

    }

    public void handleMessageAll(ArrayList<User> users) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter your Message");
            String inp = br.readLine();
            Message msg = new Message(user.getID(), inp);

            UUID conID = this.cm.newConversation();
            Conversation c = this.cm.getConversation(conID);
            c.addMember(user.getID());
            for (int i = 0; i < users.size(); i++) {
                c.addMember(users.get(i).getID());
            }

            c.sendMessage(msg);
        } catch (IOException e) {
            System.out.println("Failed to read input.");
        }
    }

    public void handleMessageAllAttendees() {
        try {
            ArrayList<Event> events = em.getEventsBySpeaker(user);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            if(events.size() > 0) {
                System.out.println("Choose one of your following talks to message:");
                int k = 0;
                for (Event e : events) {
                    System.out.println(k + ". " + e.getName());
                    k++;
                }

                String inp = br.readLine();
                Event evt = events.get(Integer.parseInt(inp));
                ArrayList<UUID> attendants = evt.getAttendees();
                ArrayList<User> attendeesUser = new ArrayList<>();
                ;
                for (int i = 0; i < attendants.size(); i++) {
                    attendeesUser.add(um.getUserByID(attendants.get(i)));
                }

                handleMessageAll(attendeesUser);
            }
            else{
                System.out.println("You are not set to speak at any event.");
            }
        } catch (IOException e) {
            System.out.println("Failed to read input.");
        }
    }

    private void serializeCM() {
        try {
            this.cg.saveToFile("cm.ser", this.cm);
        } catch (IOException e) {
            System.out.println("Something went wrong.");
        }
    }
}