package message_system;

import users.User;
import users.UserManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Presenter class.
 * Handles how the conversation screen and main screen of the message system should be presented.
 */
public class MessagePresenter {
    private User user;
    private UserManager um;

    public MessagePresenter(User user, UserManager um) {
        this.user = user;
        this.um = um;
    }

    /**
     * Creates a textual representation of the current user's conversations.
     * @param conversations The list of conversations to be showed on the screen
     * @return Formatted text of this user's list of conversations.
     */
    public String promptMainScreen(List<Conversation> conversations) {
        StringBuilder output = new StringBuilder("Contacts: \n");

        // Assuming for now only two members in a conversation
        for (int i = 0; i < conversations.size(); i++) {
            String name = "";

            if (!conversations.get(i).nameExists()) {
                ArrayList<UUID> memberIds = conversations.get(i).getMembers();
                for (UUID memberID : memberIds) {
                    if (!memberID.equals(user.getID())) {
                        name = um.getUserByID(memberID).getUsername();
                    }
                }
            } else {
                name = conversations.get(i).getName();
            }

            output.append("(").append(i).append(") ").append(name).append("\n");
        }

        return output.toString();
    }

    /**
     * Creates a textual representation of a given conversation.
     * @param c The conversation preparing to be showed on the screen
     * @return Formatted text of the messages in the conversation
     */
    public String promptConversationScreen(Conversation c) {
        ArrayList<Message> msgs = c.getMessages();
        StringBuilder output = new StringBuilder();

        for (Message msg : msgs) {
            String name = um.getUserByID(msg.getSenderID()).getUsername();
            output.append(name).append(": ").append(msg.getBody()).append("\n");
        }

        return output.toString();
    }
}
