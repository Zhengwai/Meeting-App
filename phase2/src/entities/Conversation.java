package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Entity class.
 * Handles messages and members of a conversation. Allows user to send messages.
 */
public class Conversation implements Serializable {
    private final ArrayList<UUID> members;
    private final ArrayList<UUID> messages;
    private final UUID conID;
    private String convName = null;
    private boolean readOnly = false;
    private UUID owner;

    /**
     * Initializes a conversation with no members and no messages.
     * This conversation will be serialized within the <code>ConversationManager</code>.
     */
    public Conversation() {
        this.members = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.conID = UUID.randomUUID();
    }

    /**
     * Add a message in this conversation. This method assumes that the senderID of the message is in the UserManager.
     * @param msgID The ID of the message to be sent in this conversation
     */
    public void addMessage(UUID msgID) {
        messages.add(msgID);
    }

    /**
     * Returns an ArrayList of all Messages in this conversation.
     * @return all messages in conversation.
     */
    public ArrayList<UUID> getMessages() {
        return this.messages;
    }

    public UUID getConID() {
        return this.conID;
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
     * Returns an ArrayList the userIDs of the members in this conversation.
     * @return The IDs of every member of this conversation.
     */
    public ArrayList<UUID> getMembers() {
        return this.members;
    }

    public void setName(String name) {
        this.convName = name;
    }

    public boolean nameExists() {
        return !(this.convName == null);
    }

    public String getName() {
        return this.convName;
    }

    public boolean getReadOnly() {
        return this.readOnly;
    }

    public void setReadOnly(boolean t) {
        this.readOnly = t;
    }

    public UUID getOwner() {
        return this.owner;
    }

    public void setOwner(UUID id) {
        this.owner = id;
    }

    public  boolean hasOwner() {
        return !(this.owner == null);
    }
}
