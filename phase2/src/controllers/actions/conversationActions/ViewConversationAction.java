package controllers.actions.conversationActions;

import presenters.MessagePresenter;
import use_cases.ConversationManager;
import use_cases.UserManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ViewConversationAction extends ConversationAction {

    public ViewConversationAction(UUID userID, UserManager um, ConversationManager cm, UUID conID) {
        super(userID, um, cm, conID);
    }

    public void run() throws Exception {
        if (cm.getMessagesInConversationUUID(convID).isEmpty()) {
            System.out.println("There are no messages here!");
            return;
        }

        this.mp = new MessagePresenter(userID, um, cm);
        System.out.println(mp.promptConversationScreen(convID));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;

        System.out.println("Here are the unread messages. After this session, these will be marked as read.");
        System.out.println(mp.promptMessagesScreen(cm.getUserUnreadMessages(convID, userID)));
        ArrayList<UUID> unread = new ArrayList<>(cm.getUserUnreadMessages(convID, userID));
        cm.setReadMessage(convID, userID);

        InConvActionClient icac = new InConvActionClient(userID, um, cm, convID, unread);

        try {
            do {
                List<ConversationAction> actions = icac.getUserActions();

                System.out.println("Please Enter Corresponding Choice: \n");
                for (int i = 0; i < actions.size(); i++) {
                    System.out.println((i + 1) + ". " + actions.get(i).getName());
                }
                System.out.println("'exit' to go back to main menu");
                input = br.readLine();

                if (input.matches("^[0-9]$")) {
                    int idx = Integer.parseInt(input);

                    if (0 < idx && idx < actions.size() + 1) {
                        actions.get(idx - 1).run();
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
        return "View Conversations";
    }
}
