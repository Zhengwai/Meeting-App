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
    private UserManager um;
    private EventManager em;

    public OrganizerMessageController(User inpUser, UserManager um, EventManager em) {
        this.user = inpUser;
        this.um = um;
        this.em = em;
    }

    public void run() {

        deserializeCM();
        MessagePresenter mp = new MessagePresenter(this.user, this.um);

        Conversation[] allConvos = cm.getConversations(this.user.getConversations());

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String input = null;
            while (!input.equals("exit")) {
                System.out.println("Please Enter Corresponding Choice: \n " +
                        "1. Add Friend \n " +
                        "2. New Message to specific Speakers or Attendees \n" +
                        "3. New Message to all Speakers \n" +
                        "4. New Message to all Attendees \n" +
                        "exit to exit this Controller");
                input = br.readLine();
                if (input.equals("1")) {
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
                } else if (input.equals("2")) {
                    System.out.println("Choose an option: \n" +
                            "1. Would you like to send a message in an existing conversation \n" +
                            "2. Would you like to message an individual user");
                    String inp = br.readLine();

                    if (inp.equals("1")) {
                        System.out.println("Pick one of the conversation from below:");

                        System.out.println(mp.promptMainScreen(allConvos));

                        inp = br.readLine();

                        try {
                            int index = Integer.parseInt(inp);
                            if (0 <= index && index < allConvos.length) {
                                mp.promptConversationScreen(allConvos[index]);

                                Conversation c = allConvos[index];
                                System.out.println("Enter your Message");
                                inp = br.readLine();
                                Message msg = new Message(user.getID(), inp);
                                c.sendMessage(msg);
                            } else {
                                System.out.println("There is no conversation labelled with that number.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Not a valid number.");
                        }
                    } else if (inp.equals("2")) {
                        System.out.println("Enter User's Username'");
                        inp = br.readLine();
                        User friend = um.getUserByName(input)

                        if (!user.isFriendWith(friend.getID())) {
                            System.out.println("You are not friends with " + friend.getUsername());
                            System.out.println("Would you like to add " + friend.getUsername() + " as a friend? y/n");
                            inp = br.readLine();
                            if (inp.equals("y")) {
                                User newFriend = friend;
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
                            } else if (inp.equals("n")) {
                                break;
                            } else {
                                System.out.println("You did not enter a vlid input. Will assume a no.");
                            }
                        }

                        Conversation c;
                        int j = 0;
                        boolean b = true;
                        while (j < allConvos.length && b) {
                            if (allConvos[j].getMembers().size() == 2 && allConvos[j].getMembers().contains(friend.getID())) {
                                b = false;
                                c = allConvos[j];
                            }
                        }

                        mp.promptConversationScreen(c)
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
                    Message msg = new Message(user.getID(), inp);

                    ArrayList<User> recips = um.getAllSpeakers();

                    UUID conID = this.cm.newConversation();
                    Conversation c = this.cm.getConversation(conID);
                    c.addMember(user.getID());
                    for (int i = 0; i < recips.size(); i++) {
                        c.addMember(recips.get(i).getID());
                    }

                    c.sendMessage(msg);
                } else if (input.equals("4")) {
                    System.out.println("Enter your Message");
                    String inp = br.readLine();
                    Message msg = new Message(user.getID(), inp);

                    ArrayList<User> recips = um.getAllAttendees();

                    UUID conID = this.cm.newConversation();
                    Conversation c = this.cm.getConversation(conID);
                    c.addMember(user.getID());
                    for (int i = 0; i < recips.size(); i++) {
                        c.addMember(recips.get(i).getID());
                    }

                    c.sendMessage(msg);
                } else {
                    System.out.println("You did not choose a valid option");
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