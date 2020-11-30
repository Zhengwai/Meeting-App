package controllers.actions;

import controllers.actions.conversationAction.ConversationAction;
import use_cases.ConversationManager;
import use_cases.UserManager;

import java.util.UUID;

public class GetUnreadMessagesAction extends MessageAction {
    public GetUnreadMessagesAction(UUID userID, UserManager um, ConversationManager cm) {
        super(userID, um, cm);
    }

    public void run() throws Exception {

    }

    public String getName() {
        return "";
    }
}
