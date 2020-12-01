package controllers.actions.conversationActions;

import controllers.actions.conversationActions.ConversationAction;
import presenters.MessagePresenter;
import use_cases.ConversationManager;
import use_cases.UserManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.UUID;

public class MarkUnreadAction extends ConversationAction {
    public MarkUnreadAction(UUID userID, UserManager um, ConversationManager cm, UUID convID) {
        super(userID, um, cm, convID);
    }

    public void run() {
        if (cm.getMessagesInConversationUUID(convID).isEmpty()) {
            System.out.println("There are no messages to Mark and Unread");
            return;
        }

        this.mp = new MessagePresenter(userID, um, cm);
        System.out.println(mp.promptConversationNumberedScreen(convID));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        try {
            do {
                System.out.println("Enter the corresponding number to the message you want to mark as unread, or 'exit' to go back.");
                input = br.readLine();
                List<UUID> msgs = cm.getMessagesInConversationUUID(convID);


                if (input.matches("^[0-9]$")) {
                    int idx = Integer.parseInt(input);

                    if (0 < idx && idx < msgs.size() + 1) {
                        cm.setUnreadMessage(convID, userID, msgs.get(idx - 1));
                        System.out.println("Message Marked Unread");
                        input = "exit";
                    }

                } else if (!input.equals("exit")){
                    System.out.println("Invalid input!");
                } else {
                    System.out.println("Exiting");
                }
            } while (!input.equals("exit"));
        } catch (IOException e) {
            System.out.println("Failed to read input.");
        }
    }

    public String getName() {
        return "Mark a Message as Unread";
    }

}
