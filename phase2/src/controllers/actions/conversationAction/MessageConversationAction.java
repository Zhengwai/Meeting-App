package controllers.actions.conversationAction;

import controllers.actions.conversationAction.ConversationAction;
import use_cases.ConversationManager;
import use_cases.UserManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

public class MessageConversationAction extends ConversationAction {
    public MessageConversationAction(UUID userID, UserManager um, ConversationManager cm, UUID convID) {
        super(userID, um, cm, convID);
    }

    public void run() throws Exception {
        try {
            System.out.println("Enter your Message");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = br.readLine();
            cm.sendMessageInConversation(convID, userID, input);
        } catch (IOException e) {
            System.out.println("Failed to read input.");
        }
    }

    public String getName() {
        return "";
    }
}
