package message_system;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Represents the entire system of the currently logged in user's Messages.
 * Should be accessed from a MessageManager.ser file upon instantiation.
 */
public class MessageManager implements Serializable {
    private Map<UUID, ArrayList<Message>> messages;

    /**
     * Initializes a new MessageManager for the User.
     */
    public MessageManager() {
        this.messages = new HashMap<>();
    }

    /**
     * Generates a unique random conversation ID and puts it in the messages map with an empty ArrayList.
     * @return the ID of the newly generated conversation
     */
    public UUID newConversation() {
        UUID conID = UUID.randomUUID();
        messages.put(conID, new ArrayList<>());
        return conID;
    }

    /**
     * Adds a new mapping of a conversation ID key to an empty ArrayList for when a new conversation is created.
     * @param conID the conversation ID key being put into messages
     */
    public void addConversation(UUID conID) {
        messages.put(conID, new ArrayList<>());
    }

    /**
     * Adds a Message to a value in messages based on which conversation ID key maps to it.
     * @param conID the conversation ID that the message will be added to
     * @param msg the message being added
     */
    public void sendMessage(UUID conID, Message msg) {
        this.messages.get(conID).add(msg);
    }

    /**
     * Gets the value of what the conversation ID key maps to
     * @param conID the conversation ID of the Messages being retrieved
     * @return an array of all Messages mapped from the conversation ID
     */
    public Message[] getMessages(UUID conID) {
        Message[] out = new Message[messages.get(conID).size()];
        out = messages.get(conID).toArray(out);
        return out;
    }
}
