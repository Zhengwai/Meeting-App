package controllers.actions;

import use_cases.ConversationManager;
import use_cases.EventManager;
import use_cases.UserManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MessageActionClient {
    private UUID userID;
    private UserManager um;
    private ConversationManager cm;
    private EventManager em;

    public MessageActionClient(UUID userID, UserManager um, ConversationManager cm, EventManager em) {
        this.userID = userID;
        this.um = um;
        this.cm = cm;
        this.em = em;
    }

    public List<MessageAction> getUserActions() {
        AddFriendAction addFriend = new AddFriendAction(userID, um, cm);
        GetConversationsAction getConvos = new GetConversationsAction(userID, um, cm);
        ArchiveConversationAction archiveConv = new ArchiveConversationAction(userID, um, cm);
        GetArchivedConversationsAction getArchivedConv = new GetArchivedConversationsAction(userID, um, cm);
        GetUnreadMessagesAction getUnread = new GetUnreadMessagesAction(userID, um, cm);

        ArrayList<MessageAction> out = new ArrayList<>();

        out.add(addFriend);
        out.add(getConvos);
        out.add(archiveConv);
        out.add(getArchivedConv);
        out.add(getUnread);

        // Logic for other types of users will go here
        if (um.getType(userID).equals("o")) {
            MessageAllAttendeesAction msgAttendees = new MessageAllAttendeesAction(userID, um, cm);
            MessageAllSpeakersAction msgSpeakers = new MessageAllSpeakersAction(userID, um, cm);

            out.add(msgAttendees);
            out.add(msgSpeakers);
        } else if (um.getType(userID).equals("s")) {
            MessageAllEventAttendeesAction msgAttendees = new MessageAllEventAttendeesAction(userID, um, cm, em);

            out.add(msgAttendees);
        }

        return out;
    }
}
