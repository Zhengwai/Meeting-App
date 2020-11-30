package controllers.actions;

import controllers.actions.conversationAction.*;
import presenters.MessagePresenter;
import use_cases.ConversationManager;
import use_cases.UserManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.UUID;

public class ViewConversationAction extends MessageAction {

    public ViewConversationAction(UUID userID, UserManager um, ConversationManager cm) {
        super(userID, um, cm);
    }

    private ArrayList<ConversationAction> getPossibleConversationActions(UUID c) {
        ArrayList<ConversationAction> out = new ArrayList<>();
        if (cm.hasOwner(c)) {
            if (!(cm.getReadOnly(c) && !(cm.getOwner(c).equals(userID)))) {
                MessageConversationAction msgConv = new MessageConversationAction(userID, um, cm, c);
                out.add(msgConv);

                DeleteMessageAction deleteMessage = new DeleteMessageAction(userID, um, cm, c);
                out.add(deleteMessage);
            }
        } else {
            MessageConversationAction msgConv = new MessageConversationAction(userID, um, cm, c);
            out.add(msgConv);

            DeleteMessageAction deleteMessage = new DeleteMessageAction(userID, um, cm, c);
            out.add(deleteMessage);
        }

        ArchiveMessageAction archiveMessage = new ArchiveMessageAction(userID, um, cm, c);
        out.add(archiveMessage);

        MarkUnreadAction markUnread = new MarkUnreadAction(userID, um, cm, c);
        out.add(markUnread);

        return out;
    }

    public void run() throws Exception {
        this.mp = new MessagePresenter(userID, um, cm);
        ArrayList<UUID> convos = cm.getUserConversations(userID);

        if (convos.isEmpty()) {
            System.out.println("Add a friend to start a conversation!");
            return;
        } else {
            System.out.println(mp.promptMainScreen(convos));
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        try {
            int index = Integer.parseInt(input);
            if (0 <= index && index < convos.size()) {
                String conInput;
                do {
                    //Conversation c = convos.get(index);
                    UUID cid = convos.get(index);
                    System.out.println(mp.promptConversationScreen(cid));

                    //TODO: Print Unread Messages or Have a way of indicating messages which were previously unread

                    ArrayList<ConversationAction> actions = getPossibleConversationActions(cid);


                    System.out.println("Please Enter Corresponding Choice: \n");
                    for (int i = 0; i < actions.size(); i++) {
                        System.out.println((i + 1) + ". " + actions.get(i).getName());
                    }

                    System.out.println("'exit' to go back to Messaging System.");
                    conInput = br.readLine();

                    if (input.matches("^[0-9]$")) {
                        int idx = Integer.parseInt(input);
                        if (0 < idx && idx < actions.size() + 1) {
                            actions.get(idx - 1).run();
                        } else {
                            System.out.println("Invalid Input!");
                        }
                    } else if (conInput.equals("exit")) {
                        System.out.println("Exiting");
                    } else {
                        System.out.println("Invalid input!");
                    }

                    //print possible actions for user
                    /*
                    if (c.hasOwner()) {
                        if (c.getReadOnly() && !(c.getOwner().equals(userID))) {
                            System.out.println("Type exit to leave");
                            conInput = br.readLine();
                            if (conInput.equals("exit")) {
                                break;
                            }
                        } else {
                            System.out.println("Enter your message or type 'exit' to leave.");
                            conInput = br.readLine();

                            if (conInput.equals("exit")) {
                                break;
                            }

                            cm.sendMessageInConversation(c.getID(), userID, conInput);
                        }
                    } else {
                        System.out.println("Enter your message or type 'exit' to leave.");
                        conInput = br.readLine();

                        if (conInput.equals("exit")) {
                            break;
                        }

                        cm.sendMessageInConversation(c.getID(), userID, conInput);
                    }*/
                } while (!conInput.equals("exit"));
            } else {
                System.out.println("There is no conversation labelled with that number.");
            }
        } catch (NumberFormatException e){
            System.out.println("Not a valid number.");
        } catch (IOException e) {
            System.out.println("Failed to read input.");
        }
    }

    public String getName() {
        return "View Conversations";
    }
}
