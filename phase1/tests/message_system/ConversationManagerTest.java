package message_system;

import java.util.UUID;

import static org.junit.Assert.*;

public class ConversationManagerTest {

    @org.junit.Test
    public void testNewConversation() {
        ConversationManager cm = new ConversationManager();
        UUID newConID = cm.newConversation();
    }

    @org.junit.Test
    public void testDeleteConversation() {
        ConversationManager cm = new ConversationManager();
    }

    @org.junit.Test
    public void testGetConversation() {
        ConversationManager cm = new ConversationManager();
    }
}