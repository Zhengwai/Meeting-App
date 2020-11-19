package message_system;

package users;
//import users.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.UUID;

public class SpeakerMessageController {
    private ConversationManager cm;
    private User user;
    private EventManager em;
    private UserManager um;

    public SpeakerMessageController(User inpUser, UserManager um, EventManager em) {
        this.user = inpUser;
        this.em = em;
        this.um = um;
    }

    public void run() {

        deserializeCM();
        MessagePresenter mp = new MessagePresenter(this.user, this.um);

        Conversation[] conversations = cm.getConversations(this.user.getConversations());

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String input = null;
            while (!input.equals("exit")) {
                System.out.println("Please Enter Corresponding Choice: \n " +
                        "1. New Message to all Attendees of a Talk \n " +
                        "2. Reply to Attendee Messages \n" +
                        "3. Add Friend \n" +
                        "exit to exit this Controller");
                input = br.readLine();
                if (input.equals("1")) {
                    ArrayList<Event> events;

                    for (int i = 0; i < user.getSpeakerEvents().size(); i++) {
                        events.add(em.getEventByID(user.getSpeakerEvents().get(i)));
                    }

                    System.out.println("Pick one of your Talks from below:");
                    for (int i = 0; i < events.size(); i++) {
                        System.out.println(Integer.toString(i + 1) + " " + events.get(i).getName());
                    }

                    String inp = br.readLine();
                    UUID conID = this.cm.newConversation();
                    Conversation c = this.cm.getConversation(conID);
                    ArrayList<UUID> attendees = events.get(Integer.parseInt(inp) - 1).getAttendees();
                    c.addMember(user.getID());
                    for (int i = 0; i < attendees.size(); i++) {
                        c.addMember(attendees.get(i).getID());
                    }


                    System.out.println("Enter your message");
                    inp = br.readline();

                    Message msg = new Message(user.getID(), inp);
                    c.sendMessage(msg);
                } else if (input.equals("2")) {
                    System.out.println("Pick one of the conversations from below:");

                    int j = 1;
                    ArrayList<Conversation> dms;
                    for (int i = 0; i < conversations.size(); i++) {
                        if (conversations[i].getMembers().size() == 2) {
                            System.out.println(Integer.toString(j) + conversations[i]);
                            dms.add(conversations[i]);
                            j++;
                            //TODO: Offer a better way of determining if a conversation is a message from an attendee
                        }
                    }

                    String inp = br.readLine();
                    Conversation c = dms.get(Integer.parseInt(inp));
                    System.out.println("Enter your reply");
                    inp = br.readLine();
                    Message msg = new Message(user.getID(), inp);
                    c.sendMessage(msg);
                } else if (input.equals("3")) {
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