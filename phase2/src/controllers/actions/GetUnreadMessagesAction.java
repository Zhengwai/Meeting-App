package controllers.actions;

import use_cases.ConversationManager;
import use_cases.UserManager;

import java.util.ArrayList;
import java.util.UUID;

public class GetUnreadMessagesAction extends GetConversationsAction {
    public GetUnreadMessagesAction(UUID userID, UserManager um, ConversationManager cm) {
        super(userID, um, cm);
    }

    public void run() {
        ArrayList<UUID> convos = cm.getUserConversationsUnread(userID);
        handleGetConversation("Unread Messages", convos);
    }

    public String getName() {
        return "Get Unread Conversations";
    }
}
