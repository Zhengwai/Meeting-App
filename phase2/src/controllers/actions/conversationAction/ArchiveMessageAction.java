package controllers.actions.conversationAction;

import controllers.actions.conversationAction.ConversationAction;
import use_cases.ConversationManager;
import use_cases.UserManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

public class ArchiveMessageAction extends ConversationAction {

    public ArchiveMessageAction(UUID userID, UserManager um, ConversationManager cm, UUID convID) {
        super(userID, um, cm, convID);
    }

    public void run() throws Exception {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Choose a Message to archive or type 'exit' to exit");
            System.out.print(mp.promptConversationNumberedScreen(convID));

            String input;



        } catch (IOException e) {
            System.out.println("Failed to read input.");
        }
    }

    public String getName() {
        return "";
    }
}
