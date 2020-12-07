package gateways;

import entities.Conversation;
import entities.Message;

import java.util.ArrayList;
import java.util.UUID;

public interface MessageDataGateway {
    void insertMessage(Message msg);
    void insertConversation(Conversation c);
    ArrayList<Message> fetchMessages();
    ArrayList<Message> fetchAllUserMessages(UUID userID);
    ArrayList<Conversation> fetchConversations();
    void updateConversationName(Conversation c);
    void updateConversationOwner(Conversation c);
    void updateConversationMembers(Conversation c);
    void updateConversationReadOnly(Conversation c);
}
