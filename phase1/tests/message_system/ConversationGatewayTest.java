package message_system;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class ConversationGatewayTest {

    @Test
    public void testSaveAndReadToFile() {
        ConversationGateway cg = new ConversationGateway();
        ConversationManager cm = new ConversationManager();
        UUID id = cm.newConversation();
        Conversation c = cm.getConversation(id);
        Message msg = new Message(UUID.randomUUID(), "Cringe");
        c.sendMessage(msg);

        // Saving
        try {
            cg.saveToFile("test-cm", cm);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Reading
        try {
            ConversationManager cmModified = ConversationGateway.readFromFile("test-cm");
            Conversation cModified = cmModified.getConversation(id);
            assert(cModified.getMessages()[0].getBody().equals(c.getMessages()[0].getBody()));
            assert(cModified.getMessages()[0].getSenderID().equals(c.getMessages()[0].getSenderID()));
            assert(cModified.getMessages()[0].getTimeSent().equals(c.getMessages()[0].getTimeSent()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}