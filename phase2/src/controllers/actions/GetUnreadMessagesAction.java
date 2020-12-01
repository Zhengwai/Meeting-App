package controllers.actions;

import controllers.actions.conversationActions.ViewConversationAction;
import presenters.MessagePresenter;
import use_cases.ConversationManager;
import use_cases.UserManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.UUID;

public class GetUnreadMessagesAction extends MessageAction {
    public GetUnreadMessagesAction(UUID userID, UserManager um, ConversationManager cm) {
        super(userID, um, cm);
    }

    public void run() throws Exception {
        this.mp = new MessagePresenter(userID, um, cm);
        ArrayList<UUID> convos = cm.getUserConversationsUnread(userID);

        if (convos.isEmpty()) {
            System.out.println("No Unread Messages");
            return;
        }
        System.out.println(mp.promptMainScreenCustom(convos, "Unread Conversations"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        try {
            do {
                System.out.println("Select a valid conversation to read or type 'exit' to go back to previous menu");
                input = br.readLine();

                if (input.matches("^[0-9]$")) {
                    int idx = Integer.parseInt(input);
                    if (0 < idx && idx < convos.size() + 1) {
                        ViewConversationAction vca = new ViewConversationAction(userID, um, cm, convos.get(idx - 1));
                        vca.run();
                        input = "exit";
                    }
                } else if (input.equals("exit")) {
                    System.out.println("Exiting");
                } else {
                    System.out.println("Invalid Input");
                }
            } while (!input.equals("exit"));
        } catch (IOException e) {
            System.out.println("Failed to read input.");
        }

    }

    public String getName() {
        return "Get Unread Conversations";
    }
}
