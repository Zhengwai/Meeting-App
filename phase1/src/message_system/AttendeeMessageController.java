package message_system;


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

    public AttendeeMessageController(User user, UserManager um) {
        this.user = user;
        this.um = um;
    }

    public void run() {
        deserializeCM();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            String input = br.readLine();

            // Needs while loop, this is just what input should look like and how it should be handled.
            switch (input) {
                case "add friend":
                    System.out.println("Enter the username of the person you want to add");
                    input = br.readLine();
                    User newFriend = um.getUserByName(input);

                    if (newFriend.getID() != um.NotFoundUser.getID()) {
                        user.addFriend(newFriend.getID());
                        newFriend.addFriend(user.getID());

                        UUID conID = cm.newConversation();
                        user.addConversation(conID);
                        newFriend.addConversation(conID);
                    }

                case "messages":
                    System.out.println("Getting your messages");
                    input = br.readLine();
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

    private boolean tryParseInt(String val) {
        try {
            Integer.parseInt(val);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}