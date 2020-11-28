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
public class ConversationManager {
    private final UUID userID;
    private final Map<UUID, Conversation> userConversations;
    private final Map<UUID, ArrayList<Message>> userMessages;

    /**
     * Initialize ConversationManager with no conversations
     */
    public ConversationManager(UUID userID) {
        this.userID = userID;
        this.userConversations = new HashMap<>();
        this.userMessages = new HashMap<>();
    }

    /**
     * Creates a new Conversation with a random UUID.
     * @return The conversation id of the newly created Conversation
     */
    public Conversation newConversation() {
        Conversation c = new Conversation();
        this.userConversations.put(c.getConID(), c);
        return c;
    }

    public void sendMessage(UUID conID, String body) {
        Message msg = new Message(userID, body);
        userMessages.computeIfAbsent(conID, k -> new ArrayList<>());
        userMessages.get(conID).add(msg);
        userConversations.get(conID).addMessage(msg.getMessageID());
    }

    public ArrayList<Message> getMessages(UUID conID) {
        return this.userMessages.get(conID);
    }

    public void addUserToConversation(UUID conID, UUID newUserID) {
       userConversations.get(conID).addMember(newUserID);
    }

    /**
     * Gets a conversation by its ID.
     *
     * @param conID The ID of the conversation to be returned
     * @return The conversation with the corresponding conID.
     */
    public Conversation getConversation(UUID conID) {
        return userConversations.get(conID);
    }

    /**
     * @param conIDs Array of all conversation IDs to be retrieved
     * @return All conversations that this user is member of.
     */
    public ArrayList<Conversation> getConversations(UUID[] conIDs) {
        ArrayList<Conversation> output = new ArrayList<>();
        for (UUID conID: conIDs) {
            output.add(getConversation(conID));
        }
        return output;
    }
}
