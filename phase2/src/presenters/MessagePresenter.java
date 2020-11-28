package presenters;

import entities.Conversation;
import entities.Message;
import use_cases.ConversationManager;
import use_cases.UserManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Presenter class.
 * Handles how the conversation screen and main screen of the message system should be presented.
 */
public class MessagePresenter {
    private UUID userID;
    private UserManager um;
    private ConversationManager cm;

    public MessagePresenter(UUID userID, UserManager um, ConversationManager cm) {
        this.userID = userID;
        this.um = um;
        this.cm = cm;
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
                    if (!memberID.equals(userID)) {
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
     * @param conID The ID of the conversation preparing to be showed on the screen
     * @return Formatted text of the messages in the conversation
     */

   public String promptConversationScreen(UUID conID) {
        StringBuilder output = new StringBuilder();

        // Violates clean architecture slightly. Is there a way we can present messages without needing to call
       // the message class?
        for (Message msg : cm.getMessagesInConversation(conID)) {
            String name = um.getUserByID(msg.getSenderID()).getUsername();
            output.append(name).append(": ").append(msg.getBody()).append("\n");
        }

        return output.toString();
    }
}
