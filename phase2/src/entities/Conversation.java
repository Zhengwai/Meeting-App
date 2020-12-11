package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Entity class.
 * Handles messages and members of a conversation. Allows user to send messages.
 */
public class Conversation implements Serializable {
    private final ArrayList<UUID> members;
    private final ArrayList<UUID> messages;
    private UUID conID;
    private String convName = null;
    private boolean readOnly = false;
    private UUID owner;
    private ArrayList<UUID> unreadFor = new ArrayList<>();
    private ArrayList<UUID> archivedFor = new ArrayList<>();

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
     * Sends a Message in this conversation. This method assumes that the senderID of the message is in the UserManager.
     * @param msgID The messageID to be stored in this conversation
     */
    public void addMessageID(UUID msgID) {
        messages.add(msgID);
    }

    /**
     * Returns an ArrayList of all Message IDs in this conversation.
     * @return all messages in conversation.
     */
    public ArrayList<UUID> getMessageIDs() {
        return this.messages;
    }


    public UUID getID() {
        return this.conID;
    }

    /**
     * Adds a member to this conversation.
     * @param userID The user ID of the member joining this conversation
     */
    public void addMember(UUID userID) {
        this.members.add(userID);

        ArrayList<UUID> rey = new ArrayList<>();
        this.unreadFor.add(userID);
    }

    /**
     * Removes a member from this conversation.
     * @param userID The user ID of the member being removed from this conversation
     */
    public void removeMember(UUID userID) {
        members.remove(userID);

        this.unreadFor.remove(userID);
    }

    /**
     * Returns an ArrayList the userIDs of the members in this conversation.
     * @return The IDs of every member of this conversation.
     */
    public ArrayList<UUID> getMembers() {
        return this.members;
    }

    /**
     * Setter for convName
     * @param name sets convName to name
     */
    public void setName(String name) {
        this.convName = name;
    }

    /**
     * Returns whether convName exists or not
     * @return True if convName != null. False if convName == null
     */
    public boolean nameExists() {
        return this.convName != null;
    }

    /**
     * Getter for convName
     * @return returns convName
     */
    public String getName() {
        return this.convName;
    }

    /**
     * Getter for readOnly
     * @return returns readOnly boolean
     */
    public boolean getReadOnly() {
        return this.readOnly;
    }

    /**
     * Setter for readOnly
     * @param t sets readOnly to t
     */
    public void setReadOnly(boolean t) {
        this.readOnly = t;
    }

    /**
     * Getter for owner
     * @return returns owner
     */
    public UUID getOwner() {
        return this.owner;
    }

    /**
     * Setter for owner
     * @param id sets owner to UUID id
     */
    public void setOwner(UUID id) {
        this.owner = id;
    }

    /**
     * Function for seeing if conversation has owner or not
     * @return True if owner exists, False if owner == null
     */
    public  boolean hasOwner() {
        return !(this.owner == null);
    }

    /**
     * Function for deleting a message from conversation
     * @param id Deletes message with UUID id
     */
    public void deleteMessage(UUID id) {
        this.messages.remove(id);
    }

    /**
     * Returns whether user has unread messages or not
     * @param userID UUID of user
     * @return True if user has unread messages. False if user does not.
     */
    public boolean hasUnreadMessages(UUID userID) {return this.unreadFor.contains(userID);}

    /**
     * Setter for a user to archive conversation
     * @param id UUID of user
     */
    public void setArchivedFor(UUID id) {this.archivedFor.add(id);}

    /**
     * Clears the entire archivedFor list
     */
    public void removeArchivedFor() {this.archivedFor.clear();}

    /**
     * Removes userId from ArchivedFor list
     * @param id UUID of user
     */
    public void removeArchivedForUser(UUID id) {
        this.archivedFor.remove(id);
    }

    /**
     * Returns whether the conversation is archived for user or not
     * @param id UUID of user
     * @return True if id is in archivedFor, False if not.
     */
    public boolean isArchivedFor(UUID id) {return this.archivedFor.contains(id);}

    public void setUnreadFor(UUID id) {this.unreadFor.add(id);}

    public void removeUnreadFor(UUID id){this.unreadFor.clear();}

    public void removeUnreadForUser(UUID id) {this.unreadFor.remove(id);}

    public void setConID(UUID id) {
        this.conID = id;
    }
}
