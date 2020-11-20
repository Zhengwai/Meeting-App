package message_system;

import users.User;

import javax.jws.soap.SOAPBinding;
import java.util.Arrays;
import java.util.UUID;

import static org.junit.Assert.*;
/*
public class ConversationManagerTest {

    @org.junit.Test
    public void testNewConversation() {
        ConversationManager cm = new ConversationManager();
        UUID newConID = cm.newConversation();
    }


    @org.junit.Test
    public void testGetUserConversations() {
        ConversationManager cm = new ConversationManager();
        User user1 = new User("test", "123");
        User user2 = new User("test2", "123");
        User user3 = new User("test3", "123");

        UUID conID1 = cm.newConversation();
        UUID conID2 = cm.newConversation();

        Conversation c1 = cm.getConversation(conID1);
        Conversation c2 = cm.getConversation(conID2);

        c1.addMember(user1.getID());
        c1.addMember(user2.getID());

        user1.addConversation(conID1);
        user2.addConversation(conID1);

        user1.addConversation(conID2);
        user3.addConversation(conID2);

        c2.addMember(user1.getID());
        c2.addMember(user2.getID());

        Conversation[] target = {c1, c2};
        assertArrayEquals(target, cm.getConversations(user1.getConversations()));
    }
} */