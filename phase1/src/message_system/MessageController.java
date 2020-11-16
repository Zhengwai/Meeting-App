package message_system;


import users.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.UUID;

public class MessageController {
    private ConversationManager cm;
    private UUID userID;
    private String type;

    public MessageController(UUID id, String type) {
        this.userID = id;
        this.type = type;
    }

    public void run() {

        deserializeCM();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {

            String input = br.readLine();
            while (!input.equals("exit")) {
                System.out.println("Issue Command or type exit to exit");
                input = br.readLine();
                if (input.equals("New Conversation")) {
                    System.out.println("Enter the UUIDs of Users in New Conversation. Once done enter exit.");
                    UUID conID = this.cm.newConversation();
                    Conversation c = this.cm.getConversation(conID);
                    c.addMember(userID);
                    String inp;
                    while (!input.equals("exit")) {
                        inp = br.readLine();
                        UUID id = UUID.fromString(inp);
                        c.addMember(id);
                    }
                } else if (input.equals("New Message")) {
                    System.out.println("Enter the conversation ID");
                    String inp = br.readLine();
                    UUID conID = UUID.fromString(inp);

                    System.out.println("Enter your Message");
                    inp = br.readLine();
                    Conversation c = this.cm.getConversation(conID);
                    Message msg = new Message(userID, inp);
                    c.sendMessage(msg);
                } else if (input.equals("Message All") && type == "Organizer") {
                    System.out.println("Enter your Message");
                    String inp = br.readLine();
                    //TODO: Fill recips with this user's friends.
                    ArrayList<User> recips = new ArrayList<>();
                    UUID conID = this.cm.newConversation();
                    Conversation c = this.cm.getConversation(conID);
                    c.addMember(userID);
                    for (int i = 0; i < recips.size(); i++) {
                        c.addMember(recips.get(i).getID());
                    }
                    Message msg = new Message(userID, inp);
                    c.sendMessage(msg);
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong");
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
