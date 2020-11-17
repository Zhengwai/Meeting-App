package message_system;

import users.User;
import users.UserManager;

import java.util.ArrayList;
import java.util.UUID;

public class MessagePresenter {
    private User currUser;
    private UserManager um;

    public void MessagePresenter(User user, UserManager um) {
        this.currUser = user;
        this.um = um;
    }

    public void promptMainScreen() {
        // Should output something like this

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
            output.append(name).append(": ").append(msg.getBody()).append("/n");
        }

        return output.toString();
    }
}
