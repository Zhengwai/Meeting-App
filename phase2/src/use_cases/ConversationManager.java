package use_cases;

import entities.Conversation;
import entities.Message;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Use Case class.
 * Stores every Conversation within the program (more suitable for Conversations to be stored in a DB for phase 2).
 */
public class ConversationManager implements Serializable {
    private final Map<UUID, Conversation> allConversations;
    private final Map<UUID, Message> allMessages;
    /**
     * Initialize ConversationManager with no conversations
     */
    public ConversationManager() {
        this.allConversations = new HashMap<>();
        this.allMessages = new HashMap<>();
    }

    /**
     * Creates a new Conversation with a random UUID.
     * @return The conversation id of the newly created Conversation
     */
    public UUID newConversation() {
        Conversation c = new Conversation();
        this.allConversations.put(c.getID(), c);
        return c.getID();
    }

    /**
     * Creates a new message. The message itself is stored in allMessages while its ID is stored in the conversation
     * it belongs to.
     * @param conID The ID of the conversation this message belongs to
     * @param senderID The ID of the user who sent this message
     * @param body The text content of the message.
     */
    public void sendMessageInConversation(UUID conID, UUID senderID, String body) {
        Message msg = new Message(senderID, body);
        this.allConversations.get(conID).addMessage(msg.getMessageID());
        this.allMessages.put(msg.getMessageID(), msg);
    }

    /**
     * Retrieves all message objects that belong to a given conversation.
     * @param conID The ID of the conversation to retrieve messages from.
     * @return All messages that belong to the specified conversation.
     */
    public ArrayList<Message> getMessagesInConversation(UUID conID) {
        Conversation c = allConversations.get(conID);
        ArrayList<Message> out = new ArrayList<>();

        for (UUID msgID : c.getMessageIDs()) {
            out.add(this.allMessages.get(msgID));
        }

        return out;
    }

    /**
     * Adds a user to a conversation as a member.
     * @param conID The ID of the conversation to add the user to
     * @param userID The ID of the user to add to the conversation
     */
    public void addUserToConversation(UUID conID, UUID userID) {
        this.allConversations.get(conID).addMember(userID);
    }

    /**
     * Checks whether or not a user is already in a conversation.
     * @param userID The userID to be checked
     * @param conID The conversation to check in
     * @return true if the user is a member of the conversation.
     */
    public boolean userInConversation(UUID userID, UUID conID) {
        return allConversations.get(conID).getMembers().contains(userID);
    }

    public ArrayList<Conversation> getUserConversations(UUID userID) {
        ArrayList<Conversation> out = new ArrayList<>();
        for (Conversation c : allConversations.values()) {
            if (c.getMembers().contains(userID)) {
                out.add(c);
            }
        }
        return out;
    }

    /**
     * Gets a conversation by its ID.
     * @param conID The ID of the conversation to be returned
     * @return The conversation with the corresponding conID.
     */
    public Conversation getConversation(UUID conID) {
        return allConversations.get(conID);
    }

    /**
     * @param conIDs Array of all conversation IDs to be retrieved
     * @return All conversations that this user is member of.
     */
    public ArrayList<Conversation> getConversations(UUID[] conIDs) {
        ArrayList<Conversation> output = new ArrayList<>();
        for (UUID conID: conIDs) {
            output.add(this.getConversation(conID));
        }
        return output;
    }
}
