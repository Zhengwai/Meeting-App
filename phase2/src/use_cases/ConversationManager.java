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
        this.allConversations.get(conID).addMessageID(msg.getMessageID());
        this.allMessages.put(msg.getMessageID(), msg);

        this.allConversations.get(conID).removeArchivedFor();
    }

    /**
     * Retrieves all message objects that belong to a given conversation.
     * @param conID The ID of the conversation to retrieve messages from.
     * @return All messages that belong to the specified conversation as a String Array.
     */
    public ArrayList<String[]> getMessagesInConversation(UUID conID) {
        Conversation c = allConversations.get(conID);
        ArrayList<String[]> out = new ArrayList<>();

        for (UUID msgID : c.getMessageIDs()) {
            String[] term = new String[3];
            term[0] = String.valueOf(this.allMessages.get(msgID).getSenderID());
            term[1] = String.valueOf(this.allMessages.get(msgID).getTimeSent());
            term[2] = this.allMessages.get(msgID).getBody();
            out.add(term);
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
     * Sets a user as the owner of a conversation
     * @param conID The ID of the conversation to set owner to
     * @param userID The ID of the user to make owner of conversation
    */
    public void setUserOwner(UUID conID, UUID userID) {
        this.allConversations.get(conID).setOwner(userID);
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

    /**
     * Returns all conversations that the specified user is in.
     * @param userID The ID of the user who's conversations will be returned.
     * @return List of their conversations.
     */
    public ArrayList<UUID> getUserConversations(UUID userID) {
        ArrayList<UUID> out = new ArrayList<>();
        for (Conversation c : allConversations.values()) {
            if (/*c.getMembers().contains(userID)*/    userInConversation(userID, c.getID())) {
                out.add(c.getID());
            }
        }
        return out;
    }

    /**
     * Returns all members part of a specified conversation.
     * @param conID The ID of the conversation who's members will be returned
     * @return List of member IDs.
     */
    public ArrayList<UUID> getMemberIDsInConversation(UUID conID) {
        return allConversations.get(conID).getMembers();
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
     * Sets Conversation name to given name
     * @param conID The ID of the conversation whose name will be set
     * @param name String which will become conversation name
     */
    public void setConversationName(UUID conID, String name) {
        this.allConversations.get(conID).setName(name);
    }

    /**
     * Turns on/off Conversation read only mode depending on boolean input
     * @param conID The ID of the conversation which will be set to read only mode
     * @param b The boolean which determines whether read only mode is set on/off
     */
    public void setConversationReadOnly(UUID conID, boolean b) {
        this.allConversations.get(conID).setReadOnly(b);
    }

    /**
     * Returns whether conversation has owner
     * @param conID Conversation ID for conversation which will be checked
     * @return Boolean. True if there exists an Owner and False if there does not.
     */
    public boolean hasOwner(UUID conID) {return this.allConversations.get(conID).hasOwner();}

    public boolean getReadOnly(UUID conID) {return this.allConversations.get(conID).getReadOnly();}

    public UUID getOwner(UUID conID) {return this.allConversations.get(conID).getOwner();}

    public boolean noNameExists(UUID conID) {return !this.allConversations.get(conID).nameExists();}

    public String getName(UUID conID) {return this.allConversations.get(conID).getName();}

    public ArrayList<UUID> getUserConversationsNotArchived(UUID userID) {
        ArrayList<UUID> out = new ArrayList<>();
        for (Conversation c : allConversations.values()) {
            if (userInConversation(userID, c.getID())) {
                if (!c.isArchivedFor(userID)) {
                    out.add(c.getID());
                }
            }
        }
        return out;
    }

    public ArrayList<UUID> getUserConversationsArchived(UUID userID) {
        ArrayList<UUID> out = new ArrayList<>();
        for (Conversation c : allConversations.values()) {
            if (userInConversation(userID, c.getID())) {
                if (c.isArchivedFor(userID)) {
                    out.add(c.getID());
                }
            }
        }
        return out;
    }

    public ArrayList<UUID> getUserConversationsUnread(UUID userID) {
        ArrayList<UUID> out = new ArrayList<>();
        for (Conversation c : allConversations.values()) {
            if (userInConversation(userID, c.getID())) {
                if (c.hasUnreadMessages(userID)) {
                    out.add(c.getID());
                }
            }
        }
        return out;
    }

    public ArrayList<UUID> getUserUnreadMessages(UUID conID, UUID userID) {
        return this.allConversations.get(conID).getUnreadMessages(userID);
    }

    public void setArchivedMessage(UUID conID, UUID userID) {
        this.allConversations.get(conID).setArchivedFor(userID);
    }

    public void setReadMessage(UUID conID, UUID userID) {this.allConversations.get(conID).removeUnreadMessage(userID);}

    public void setUnreadMessage(UUID conID, UUID userID, UUID msgID) {this.allConversations.get(conID).addUnreadMessage(msgID, userID);}

    public ArrayList<UUID> getMessagesInConversationUUID(UUID conID) {return this.allConversations.get(conID).getMessageIDs();}

    public void deleteMessage(UUID conID, UUID msgID) {
        this.allConversations.get(conID).deleteMessage(msgID);
        this.allMessages.remove(msgID);
    }

    public ArrayList<String[]> getMessagesInList(ArrayList<UUID> msgIDs) {
        ArrayList<String[]> out = new ArrayList<>();

        for (UUID msgID : msgIDs) {
            String[] term = new String[3];
            term[0] = String.valueOf(this.allMessages.get(msgID).getSenderID());
            term[1] = String.valueOf(this.allMessages.get(msgID).getTimeSent());
            term[2] = this.allMessages.get(msgID).getBody();
            out.add(term);
        }

        return out;
    }
}
