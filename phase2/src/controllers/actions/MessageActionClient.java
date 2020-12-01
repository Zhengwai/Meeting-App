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
        ArrayList<MessageAction> out = new ArrayList<>();

        // Logic for other types of users will go here
        out.add(addFriend);
        out.add(getConvos);

        return out;
    }
}
