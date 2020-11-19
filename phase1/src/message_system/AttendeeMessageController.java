package message_system;


import ScheduleSystem.EventManager;
import users.User;
import users.UserManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.UUID;

public class AttendeeMessageController {
    private ConversationManager cm;
    private User user;
    private UserManager um;
    private EventManager em;

    public AttendeeMessageController(User user, UserManager um,  EventManager em) {
        this.user = user;
        this.um = um;
        this.em = em;
    }

    public void run() {
        deserializeCM();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        MessagePresenter mp = new MessagePresenter(this.user, this.um);

        try {
            String input = "";

            // Needs while loop, this is just what input should look like and how it should be handled.
            while (!input.equals("exit")) {
                System.out.println("Please Enter Corresponding Choice: \n " +
                        "1. Add Friend \n " +
                        "2. Message Existing Conversation \n" +
                        "exit to exit this Controller");
                input = br.readLine();

                switch (input) {
                    case "1":
                        System.out.println("Enter the username of the person you want to add");
                        input = br.readLine();
                        User newFriend = um.getUserByName(input);

                        if (newFriend.getID() != um.NotFoundUser.getID()) {
                            UUID conID = cm.newConversation();
                            Conversation c = cm.getConversation(conID);

                            user.addFriend(newFriend.getID());
                            newFriend.addFriend(user.getID());

                            user.addConversation(conID);
                            newFriend.addConversation(conID);

                            c.addMember(user.getID());
                            c.addMember(newFriend.getID());
                        }
                        break;

                    case "2":
                        Conversation[] conversations = cm.getConversations(this.user.getConversations());
                        System.out.println(mp.promptMainScreen(conversations));

                        System.out.println("Enter the number of the conversation to open:");
                        input = br.readLine();
                        try {
                            int index = Integer.parseInt(input);
                            if (0 <= index && index < conversations.length) {
                                Conversation c = conversations[index];
                                mp.promptConversationScreen(c);
                                System.out.println("Enter your message");
                                input = br.readLine();
                                Message msg = new Message(user.getID(), input);
                                c.sendMessage(msg);
                            } else {
                                System.out.println("There is no conversation labelled with that number.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Not a valid number.");
                        }
                        break;
                    default:
                        if (!input.equals("exit")) {
                            System.out.println("Chose invalid option");
                        }
                }
            }




        } catch (Exception e) {
            System.out.println("Something went wrong in the message controller.");
        }
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