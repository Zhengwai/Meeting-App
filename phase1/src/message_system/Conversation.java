package message_system;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Entity (I think? Since ConversationManager would be Use Case) class.
 * Stores messages sent from members of this conversation.
 */
public class Conversation implements Serializable {
    private final ArrayList<UUID> members;
    private final ArrayList<Message> messages;

    /**
     * Initializes a conversation with no members and no messages
     */
    public Conversation() {
        this.members = new ArrayList<>();
        this.messages = new ArrayList<>();
    }

    /**
     * Sends a Message in this conversation.
     * @param msg The message to be sent in this conversation
     */
    public void sendMessage(Message msg) {
        messages.add(msg);
    }

    /**
     * Gets all messages in this conversation.
     * @return An array of all messages in this conversation
     */
    public Message[] getMessages() {
        Message[] out = new Message[messages.size()];
        out = this.messages.toArray(out);
        return out;
    }

    /**
     * Adds a member to this conversation.
     * @param userID The user ID of the member joining this conversation
     */
    public void addMember(UUID userID) {
        this.members.add(userID);
    }

    /**
     * Removes a member from this conversation.
     * @param userID The user ID of the member being removed from this conversation
     */
    public void removeMember(UUID userID) {
        members.remove(userID);
    }

    /**
     * Gets all members of this conversation.
     * @return An array of all the members' IDs.
     */
    public UUID[] getMembers() {
        UUID[] out = new UUID[this.members.size()];
        out = this.members.toArray(out);
        return out;
    }
}