package message_system;


import ScheduleSystem.EventManager;
import users.User;
import users.UserGateway;
import users.UserManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.UUID;

public class AttendeeMessageController {
    private ConversationGateway cg;
    private ConversationManager cm;
    private MessagePresenter mp;
    private User user;
    private UserManager um;
    private EventManager em;

    // ASSUME UM ALREADY DESERIALIZED
    public AttendeeMessageController(User user, UserManager um, EventManager em) {
        this.user = user;
        this.cg = new ConversationGateway();
        deserializeCM();
        this.um = um;
        this.mp = new MessagePresenter(this.user, this.um);
        this.em = em;
    }

    public void run() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            String input = "";
            while (!input.equals("exit")) {
                System.out.println("Please Enter Corresponding Choice: \n " +
                        "1. Add Friend \n " +
                        "2. Message Existing Conversation \n" +
                        "'exit' to return to the main menu.");
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
                    default:
                        if (!input.equals("exit")) {
                            System.out.println("Chose invalid option");
                        }
                }
            }
            serializeCM();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleAddFriend(String input) {
        User newFriend = um.getUserByName(input);
        UUID conID = cm.newConversation();

        if (newFriend.getID() == um.NotFoundUser.getID()) {
            System.out.println("That user doesn't exist!");
            return;
        }

        if (newFriend.getID() == user.getID()) {
            System.out.println("You can't add yourself as a friend.");
            return;
        }

        for (UUID u: user.getConversations()) {
            if (cm.getConversation(u).getMembers().contains(newFriend.getID())) {
                System.out.println("You already have a conversation with this user!");
                return;
            }
        }
        um.addFriends(user, newFriend);
        cm.addUserToConversation(conID, user.getID());
        cm.addUserToConversation(conID, newFriend.getID());
        user.addConversation(conID);
        newFriend.addConversation(conID);
        System.out.println("You and " + newFriend.getUsername() + " are now friends!");
    }


    public void handleConversations(String input, ArrayList<Conversation> conversations) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            int index = Integer.parseInt(input);
            if (0 <= index && index < conversations.size()) {
                String conInput;
                while (true) {
                    Conversation c = conversations.get(index);
                    System.out.println(mp.promptConversationScreen(c));
                    System.out.println("Enter your message or type 'exit' to leave.");
                    conInput = br.readLine();

                    if (conInput.equals("exit")) {
                        break;
                    }

                    Message msg = new Message(user.getID(), conInput);
                    c.sendMessage(msg);
                }
            } else {
                System.out.println("There is no conversation labelled with that number.");
            }
        } catch (NumberFormatException e){
            System.out.println("Not a valid number.");
        } catch (IOException e) {
            System.out.println("Failed to read input.");
        }
    }

    private void deserializeCM() {
        try {
            this.cm = this.cg.readFromFile("cm.ser");
        } catch (ClassNotFoundException e) {
            System.out.println("Couldn't find the cm.ser file. Check phase1 directory.");
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