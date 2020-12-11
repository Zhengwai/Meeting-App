package use_cases;

import database.MessageDataMapper;
import database.UserDataMapper;
import entities.Conversation;
import entities.Message;
import gateways.MessageDataGateway;
import gateways.UserDataGateway;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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
    private MessageDataGateway mdg = new MessageDataMapper();
    private PropertyChangeSupport  observable;

    /**
     * Initialize ConversationManager with no conversations
     */
    public ConversationManager() {
        this.allConversations = new HashMap<>();
        this.allMessages = new HashMap<>();
        retrieveDataFromDB();
        this.observable = new PropertyChangeSupport (this);
    }

    // Put this at bottom when done
    private void retrieveDataFromDB() {
        ArrayList<Message> msgs = mdg.fetchMessages();
        ArrayList<Conversation> cvs = mdg.fetchConversations();

        for (Message msg: msgs) {
            allMessages.put(msg.getMessageID(), msg);
        }

        for (Conversation con: cvs) {
            allConversations.put(con.getID(), con);
        }
    }

    /**
     * Creates a new Conversation with a random UUID.
     * @return The conversation id of the newly created Conversation
     */
    public UUID newConversation() {
        Conversation c = new Conversation();
        this.allConversations.put(c.getID(), c);
        mdg.insertConversation(c);
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
        mdg.insertNewMessage(msg);

        this.allConversations.get(conID).removeArchivedFor();
        for (UUID id : this.allConversations.get(conID).getMembers()) {
            if (!id.equals(senderID)) {
                this.allConversations.get(conID).setUnreadFor(id);
            }
        }
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
            String[] term = new String[4];
            term[0] = String.valueOf(this.allMessages.get(msgID).getSenderID());
            term[1] = String.valueOf(this.allMessages.get(msgID).getTimeSent());
            term[2] = this.allMessages.get(msgID).getBody();
            term[3] = String.valueOf(msgID);
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
        mdg.updateConversationMembers(getConversation(conID));

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

    /**
     * Getter for Conversation.getReadOnly()
     * @param conID UUID of conversation
     * @return boolean value of Conversation.getReadOnly(). True if conversation is readOnly and False if not
     */
    public boolean getReadOnly(UUID conID) {return this.allConversations.get(conID).getReadOnly();}

    /**
     * Getter for Conversation.getOwner(). Assumes Conversation has an Owner
     * @param conID UUID of conversation
     * @return UUID of the owner of the conversation
     */
    public UUID getOwner(UUID conID) {return this.allConversations.get(conID).getOwner();}

    /**
     * Function to remove a member from a conversation
     * @param conID UUID of the conversation that the member will be removed from
     * @param userID UUID of the user who will be removed from given conversation
     */
    public void removeMember(UUID conID, UUID userID) {this.allConversations.get(conID).removeMember(userID);}

    /**
     * Returns whether conversation has a name or not
     * @param conID UUID of the conversation in question
     * @return boolean. True if Conversation does NOT have a name. False if Conversation does have a name.
     */
    public boolean noNameExists(UUID conID) {return !this.allConversations.get(conID).nameExists();}

    /**
     * Getter for Conversation Name. Assumes conversation has a name
     * @param conID UUID of Conversation in question
     * @return String value of Conversation Name
     */
    public String getName(UUID conID) {return this.allConversations.get(conID).getName();}

    /**
     * Returns a list of UUIDs of conversations which are not archived for user.
     * @param userID UUID of the user in question
     * @return list of UUIDs of conversations which are not archived for user.
     */
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

    /**
     * Returns a list of UUIDs of conversations which are archived for user.
     * @param userID UUID of the user in question
     * @return list of UUIDs of conversations which are archived for user.
     */
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

    /**
     * Return a list of UUIDs of conversations which have unread messages for the given user
     * @param userID UUID of user in question
     * @return list of UUIDs of conversations which have unread messages for the given user
     */
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

    /**
     * Set a given conversation as archived for a given user
     * @param conID UUID of conversation that is being archived
     * @param userID UUID of user who is archiving the conversation for themself
     */
    public void setArchivedMessage(UUID conID, UUID userID) {
        this.allConversations.get(conID).setArchivedFor(userID);
        updateMessages();

    }

    public void removeArchivedMessageForUser(UUID conID, UUID userID) {
        this.allConversations.get(conID).removeArchivedForUser(userID);
        updateMessages();
    }

    /**
     * Clears all unread messages a given user has in a given conversation
     * @param conID UUID of conversation in quesiton
     * @param userID UUID of user whose unread messages are being cleared
     */
    public void setReadMessage(UUID conID, UUID userID) {
        this.allConversations.get(conID).removeUnreadForUser(userID);
        updateMessages();
    }

    /**
     * Adds a given UUID of a message to the list of unread messages a given user has in a given conversation
     * @param conID UUID of conversation in question
     * @param userID UUID of user in question
     */
    public void setUnreadMessage(UUID conID, UUID userID) {
        this.allConversations.get(conID).setUnreadFor(userID);
        updateMessages();
    }

    /**
     * Return a list of UUIDs of the Messages in a given conversation
     * @param conID UUID of Conversation in question
     * @return list of UUIDs of the Messages in a given conversation
     */
    public ArrayList<UUID> getMessagesInConversationUUID(UUID conID) {return this.allConversations.get(conID).getMessageIDs();}

    /**
     * Delete a given UUID of a message from a conversation
     * @param conID UUID of conversation that message is going to be deleted from
     * @param msgID UUID of message that is being deleted
     */
    public void deleteMessage(UUID conID, UUID msgID) {
        this.allConversations.get(conID).deleteMessage(msgID);
        this.allMessages.remove(msgID);
    }

    public void deleteConversation(UUID conID){
        this.allConversations.remove(conID);
    }

    /**
     * Return a list of String Arrays, that contains the senderID, Time and Body of a message, for each message inside a
     * corresponding given list of message UUIDs
     * @param msgIDs List of UUIDs of messages that are to be returned in String array form
     * @return list of String Arrays, that contains the senderID, Time and Body of a message, for each message inside a
     * corresponding given list of message UUIDs
     */
    public ArrayList<String[]> getMessagesInList(ArrayList<UUID> msgIDs) {
        ArrayList<String[]> out = new ArrayList<>();

        for (UUID msgID : msgIDs) {
            String[] term = new String[4];
            term[0] = String.valueOf(this.allMessages.get(msgID).getSenderID());
            term[1] = String.valueOf(this.allMessages.get(msgID).getTimeSent());
            term[2] = this.allMessages.get(msgID).getBody();
            term[3] = msgID.toString();
            out.add(term);
        }

        return out;
    }

    public void addObserver(PropertyChangeListener observer) {
        observable.addPropertyChangeListener("location", observer);
    }

    public void notifyObservers (PropertyChangeEvent newEvent)
    {
        for ( PropertyChangeListener observer : observable.getPropertyChangeListeners()){
            observer.propertyChange(newEvent);
            System.out.println(observer);
        }
    }

    public void updateMessages() {
        PropertyChangeEvent newEvent = new PropertyChangeEvent (this, "location", "a", "b");
        notifyObservers (newEvent);

    }

    public void removePropertyChangeListener (String propertyName, PropertyChangeListener listener) {
        observable.removePropertyChangeListener (propertyName, listener);
    }

    public Boolean conversationArchived(UUID conID, UUID userID){
        return getUserConversationsArchived(userID).contains(conID);
    }
}

