package message_system;

package users;
//import users.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.UUID;

public class AttendeeMessageController {
    private ConversationManager cm;
    private User user;
    private EventManager em;

    public AttendeeMessageController(User inpUser) {
        this.user = inpUser;
    }

    public void run() {
        deserializeCM();

        ArrayList<Conversation> allConvos = this.cm.getAllConversations(user);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String input;
            while (!input.equals("exit")) {
                System.out.println("Please Enter Corresponding Choice: \n " +
                        "1. New Message to an Attendee or a Speaker \n " +
                        "exit to exit this Controller");
                input = br.readLine();
                if (input.equals("1")) {
                    System.out.println("Who would you like to message");
                    //TODO: UUID for now but change later
                    String inp;
                    inp = br.readLine();
                    int i = 0;
                    boolean b = true;
                    while (i < allConvos.size() && b) {
                        if (allConvos.get(i).getMembers().size() == 2 && allConvos.get(i).getMembers().contains(UUID.fromString(inp))) {
                            b = false;
                        } else {
                            i++;
                        }
                    }
                    Conversation c;
                    if (!b) {
                        c = allConvos.get(i);
                    } else {
                        UUID conID = this.cm.newConversation();
                        c = this.cm.getConversation(conID);
                        c.addMember(user.getID());
                        c.addMember(UUID.fromString(inp));
                    }

                    System.out.println("Enter your Message");
                    inp = br.readLine();
                    Message msg = new Message(user.getID(), inp);
                    c.sendMessage(msg);

                } else {
                    System.out.println("You did not chosoe a valid Options");
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