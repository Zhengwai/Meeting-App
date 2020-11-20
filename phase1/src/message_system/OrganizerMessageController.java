package message_system;


import ScheduleSystem.EventManager;
import users.User;
import users.UserManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.UUID;

public class OrganizerMessageController extends AttendeeMessageController {


    public OrganizerMessageController(User inpUser, UserManager um, EventManager em) {
        super(inpUser, um, em);
    }

    public void run() {
        MessagePresenter mp = new MessagePresenter(this.user, this.um);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String input = "";
            while (!input.equals("exit")) {
                System.out.println("Please Enter Corresponding Choice: \n " +
                        "1. Add Friend \n " +
                        "2. Message Existing Conversation \n" +
                        "3. New Message to all Speakers \n" +
                        "4. New Message to all Attendees \n" +
                        "exit to exit this Controller");
                input = br.readLine();

                switch (input) {
                    case "1":
                        System.out.println("Enter the username of the person you want to add");
                        input = br.readLine();
                        handleAddFriend(input);
                        break;

                    case "2":
                        ArrayList<Conversation> conversations = cm.getConversations(this.user.getConversations());
                        System.out.println(mp.promptMainScreen(conversations));
                        System.out.println("Enter the number of the conversation to open:");
                        input = br.readLine();
                        handleConversations(input, conversations);
                        break;

                    case "3":
                        handleMessageAllSpeakers();
                        break;

                    case "4":
                        handleMessageAllAttendees();
                        break;

                    default:
                        if (!input.equals("exit")) {
                            System.out.println("Chose invalid option.");
                        }
                        break;
                }
            }
            serializeCM();
        } catch (IOException e) {
            System.out.println("Something went wrong");
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
        handleMessageAll(this.um.getAllAttendees());
    }

    public void handleMessageAllSpeakers() {
        handleMessageAll(this.um.getAllSpeakersUser());
    }

    private void serializeCM() {
        try {
            this.cg.saveToFile("cm.ser", this.cm);
        } catch (IOException e) {
            System.out.println("Something went wrong.");
        }
    }
}