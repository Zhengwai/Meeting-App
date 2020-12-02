package controllers.actions.conversationActions;

import use_cases.ConversationManager;
import use_cases.UserManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InConvActionClient {
    private UUID userID;
    private UserManager um;
    private ConversationManager cm;
    private UUID conID;
    private ArrayList<UUID> unread;

    public InConvActionClient(UUID userID, UserManager um, ConversationManager cm, UUID conID, ArrayList<UUID> unread) {
        this.userID = userID;
        this.um = um;
        this.cm = cm;
        this.conID = conID;
        this.unread = unread;
    }

    public List<ConversationAction> getUserActions() {
        ArrayList<ConversationAction> out = new ArrayList<>();


        GetUnreadMessagesInConvAction unreadMessage = new GetUnreadMessagesInConvAction(userID, um, cm, conID, unread);
        MarkUnreadAction markUnread = new MarkUnreadAction(userID, um, cm, conID);


        out.add(unreadMessage);
        out.add(markUnread);


        if (cm.hasOwner(conID)) {
            if (!(cm.getReadOnly(conID) && !(cm.getOwner(conID).equals(userID)))) {
                DeleteMessageAction deleteMessage = new DeleteMessageAction(userID, um, cm, conID);
                out.add(deleteMessage);
                MessageConversationAction messageConv = new MessageConversationAction(userID, um, cm, conID);
                out.add(messageConv);
                RemoveMemberAction removeMember = new RemoveMemberAction(userID, um, cm, conID);
                out.add(removeMember);
            }
        } else {
            DeleteMessageAction deleteMessage = new DeleteMessageAction(userID, um, cm, conID);
            out.add(deleteMessage);
            MessageConversationAction messageConv = new MessageConversationAction(userID, um, cm, conID);
            out.add(messageConv);
            RemoveMemberAction removeMember = new RemoveMemberAction(userID, um, cm, conID);
            out.add(removeMember);
        }


        return out;
    }
}
