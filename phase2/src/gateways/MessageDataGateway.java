package gateways;

import entities.Conversation;
import entities.Message;

import java.util.ArrayList;

/**
 * Gateway class.
 * Allows for communication between the ConversationManager and the database while still adhering to clean architecture.
 */
public interface MessageDataGateway {

    /**
     * Adds a new message to the database. This method assumes that this message does not already exist in the database.
     * @param msg The message to be inserted.
     */
    void insertNewMessage(Message msg);

    /**
     * Adds a new conversation to the database. This method assumes that this conversation does not already exist in the
     * database.
     * @param c The conversation to be inserted.
     */
    void insertConversation(Conversation c);


    /**
     * Retrieves *all* messages stored in the database.
     * @return ArrayList of all messages.
     */
    ArrayList<Message> fetchMessages();


    /**
     * Retrieves *all* conversations from the database.
     * @return ArrayList of all conversations.
     */
    ArrayList<Conversation> fetchConversations();

    /**
     * Updates the Name field of a conversation in the database.
     * @param c The conversation being updated.
     */
    void updateConversationName(Conversation c);

    /**
     * Updates the Owner field of a conversation in the database.
     * @param c The conversation being updated.
     */
    void updateConversationOwner(Conversation c);

    /**
     * Updates the Members field of a conversation in the database.
     * @param c The conversation being updated.
     */
    void updateConversationMembers(Conversation c);

    /**
     * Updates the ReadOnly field of a conversation in the database.
     * @param c The conversation being updated.
     */
    void updateConversationReadOnly(Conversation c);

    /**
     * Updates the ArchivedFor field of a conversation in the database.
     * @param c The conversation being updated.
     */
    void updateConversationArchivedFor(Conversation c);

    /**
     * Updates the UnreadFor field of a conversation in the database.
     * @param c The conversation being updated.
     */
    void updateConversationUnreadFor(Conversation c);

    /**
     * Updates the messages field of a conversation in the database.
     * @param c The conversation being updated.
     */
    void updateConversationMsgIDs(Conversation c);
}
