package gateways;

import entities.Conversation;
import entities.Message;

import java.util.ArrayList;
import java.util.UUID;

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
     * @param c
     */
    void updateConversationName(Conversation c);

    /**
     * Updates the Name field of a conversation in the database.
     * @param c
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
}
