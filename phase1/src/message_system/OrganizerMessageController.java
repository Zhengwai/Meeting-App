package message_system;


import users.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.UUID;

public class OrganizerMessageController {
    private ConversationManager cm;
    private User user;
    private Conversation[] myConvos;

    public OrganizerMessageController(User inpUser) {
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
                        "1. New Message to specific Speakers or Attendees \n " +
                        "2. New Message to specific Speakers or Attendees \n" +
                        "3. New Message to all Speakers \n" +
                        "4. New Message to all Attendees \n" +
                        "exit to exit this Controller");
                input = br.readLine();
                if (input.equals("1")) {
                    System.out.println("Enter the UUIDs of Users in New Conversation. Once done enter exit.");
                    //TODO: Change UUIDs to Usernames or something of the kind
                    UUID conID = this.cm.newConversation();
                    Conversation c = this.cm.getConversation(conID);
                    c.addMember(user.getID());
                    String inp;
                    while (!input.equals("exit")) {
                        inp = br.readLine();
                        UUID id = UUID.fromString(inp);
                        c.addMember(id);
                    }
                } else if (input.equals("2")) {
                    System.out.println("Choose an option: \n" +
                            "1. Would you like to send a message in an existing conversation \n" +
                            "2. Would you like to message an individual user");
                    String inp = br.readLine();

                    if (inp.equals("1")) {
                        System.out.println("Pick one of the conversation from below:");
                        for (int i = 0; i < allConvos.size(); i++) {
                            System.out.println(Integer.toString(i + 1) + allConvos.get(i));
                            //TODO: Way of printing a Conversation
                        }

                        inp = br.readLine();
                        Conversation c = allConvos.get(Integer.parseInt(inp) - 1)

                        System.out.println("Enter your Message");
                        inp = br.readLine();
                        Message msg = new Message(user.getID(), inp);
                        c.sendMessage(msg);
                    } else if (inp.equals("2")) {
                        System.out.println("Enter User UUID");
                        inp = br.readLine();
                        int i = 0;
                        boolean b = true;
                        while (i < allConvos.size() && b) {
                            if (allConvos.get(i).getMembers().size == 2 && allConvos.get(i).getMembers().contains(UUID.fromString(inp))) {
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
                        System.out.println("You did not choose a valid option");
                    }
                } else if (input.equals("3")) {
                    System.out.println("Enter your Message");
                    String inp = br.readLine();
                    Message msg = new Message(userID, inp);

                    //TODO: Assign recips to list of all Speakers.
                    ArrayList<User> recips = new ArrayList<>();
                    UUID conID = this.cm.newConversation();
                    Conversation c = this.cm.getConversation(conID);
                    c.addMember(userID);
                    for (int i = 0; i < recips.size(); i++) {
                        c.addMember(recips.get(i).getID());
                    }

                    c.sendMessage(msg);
                } else if (input.equals("4")) {
                    System.out.println("Enter your Message");
                    String inp = br.readLine();
                    Message msg = new Message(userID, inp);

                    //TODO: Assign recips to list of all Attendees.
                    ArrayList<User> recips = new ArrayList<>();
                    UUID conID = this.cm.newConversation();
                    Conversation c = this.cm.getConversation(conID);
                    c.addMember(userID);
                    for (int i = 0; i < recips.size(); i++) {
                        c.addMember(recips.get(i).getID());
                    }

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