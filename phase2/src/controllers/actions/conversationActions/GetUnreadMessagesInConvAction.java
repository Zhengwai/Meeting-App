package controllers.actions.conversationActions;

import use_cases.ConversationManager;
import use_cases.UserManager;

import java.util.ArrayList;
import java.util.UUID;

public class GetUnreadMessagesInConvAction extends ConversationAction {
    ArrayList<UUID> unread;

    public GetUnreadMessagesInConvAction(UUID userID, UserManager um, ConversationManager cm, UUID conID, ArrayList<UUID> unread) {
        super(userID, um, cm, conID);
        this.unread = unread;
    }

    public void run() {
        System.out.println("Here are the unread messages before this session. After this session, these will be marked as read");
        System.out.println(mp.promptMessagesScreen(unread));

        if (!(cm.getUserUnreadMessages(convID, userID).isEmpty())) {
            System.out.println("Here are the newly marked unread messages. These will be marked unread till next session.");
        }
    }

    public String getName() {
        return "View Unread Messages";
    }
}
