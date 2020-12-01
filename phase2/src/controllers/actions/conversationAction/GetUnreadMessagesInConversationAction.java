package controllers.actions.conversationAction;

import controllers.actions.conversationAction.ConversationAction;
import use_cases.ConversationManager;
import use_cases.UserManager;

import java.util.UUID;

public class GetUnreadMessagesInConversationAction extends ConversationAction {
    public GetUnreadMessagesInConversationAction(UUID userID, UserManager um, ConversationManager cm, UUID convID) {
        super(userID, um, cm, convID);
    }

    public void run() throws Exception {

    }

    public String getName() {
        return "";
    }
}
