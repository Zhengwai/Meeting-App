package message_system;

import users.User;
import users.UserManager;
import java.util.UUID;

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
    public String promptMainScreen(Conversation[] conversations) {
        StringBuilder output = new StringBuilder("Contacts: \n");

        // Assuming for now only two members in a conversation
        for (int i = 0; i < conversations.length; i++) {
            UUID[] memberIds = conversations[i].getMembers();
            String name;

            if (memberIds[0] == user.getID()) name = um.getUserByID(memberIds[1]).getUsername();
            else name = um.getUserByID(memberIds[0]).getUsername();

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
        Message[] msgs = c.getMessages();
        StringBuilder output = new StringBuilder();

        for (Message msg : msgs) {
            String name = um.getUserByID(msg.getSenderID()).getUsername();
            output.append(name).append(": ").append(msg.getBody()).append("\n");
        }

        return output.toString();
    }
}
