package message_system;


import users.User;
import ScheduleSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.UUID;

public class SpeakerMessageController extends AttendeeMessageController {
    private ConversationGateway cg;
    private ConversationManager cm;
    private MessagePresenter mp;
    private User user;
    private Conversation[] myConvos;
    private EventManager em;

    public SpeakerMessageController(User inpUser, UserManager um, EventManager em) {
        super(User inpUser, UserManager um, EventManager em);
    }

    public void run() {

        deserializeCM();


        ArrayList<Conversation> allConvos = this.cm.getConversations(user.getConversations());

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String input = null;
            while (!input.equals("exit")) {
                System.out.println("Please Enter Corresponding Choice: \n " +
                        "1. Add Friend \n " +
                        "2. Message Existing Conversation \n" +
                        "3. New Message to all attendees of a talk \n" +
                        "exit to exit this Controller");
                input = br.readLine();
                if (input.equals("1")) {
                    System.out.println("Enter the username of the person you want to add");
                    input = br.readLine();
                    handleAddFriend(input);
                } else if (input.equals("2")) {
                    ArrayList<Conversation> conversations = cm.getConversations(this.user.getConversations());
                    System.out.println(mp.promptMainScreen(conversations));
                    System.out.println("Enter the number of the conversation to open:");
                    input = br.readLine();
                    handleConversations(input, conversations);
                    break;
                } else if (input.equals("3")) {
                    handleMessageAllAttendees();
                } else {
                    System.out.println("You did not chosoe a valid Options");
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }

    }

    public void handleMessageAll(ArrayList<User> users) {
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
    }

    public void handleMessageAllAttendees() {
        ArrayList<Event> events;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < user.getSpeakerEvents().size(); i++) {
            events.add(em.getEventByID(user.getSpeakerEvents().get(i)));
        }
        System.out.println("Choose one of your following talks to message:");
        for (int i = 0; i < events.size(); i++) {
            System.out.println(Integer.toString(i) + ". " + events.get(i).name());
        }

        string inp = br.readLine();
        Event evt = events.get(Integer.parseInt(inp) - 1);
        ArrayList<User> users = evt.getAttendees();

        handleMessageAll(users);
    }

    private void deserializeCM() {
        ConversationGateway cg = new ConversationGateway();
        try {
            this.cm = cg.readFromFile("cm.ser");
        } catch (ClassNotFoundException e) {
            System.out.println("Couldn't find the cm.ser file. Check phase1 directory.");
        }
    }
}