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

    void insertConversation(Conversation c);


    /**
     * Retrieves *all* messages stored in the database.
     * @return ArrayList of all messages.
     */
    ArrayList<Message> fetchMessages();


    ArrayList<Conversation> fetchConversations();
    void updateConversationName(Conversation c);
    void updateConversationOwner(Conversation c);
    void updateConversationMembers(Conversation c);
    void updateConversationReadOnly(Conversation c);
}
