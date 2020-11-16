package message_system;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class ConversationTest {

    @Test
    public void testSendMessage() {
        Conversation c = new Conversation();
        Message msg = new Message(UUID.randomUUID(), "Hello");
        c.sendMessage(msg);
        assertArrayEquals(c.getMessages(), new Message[]{msg});
    }

    @Test
    public void testGetMessages() {
        Conversation c = new Conversation();
        Message msg1 = new Message(UUID.randomUUID(), "Hello");
        Message msg2 = new Message(UUID.randomUUID(), "Hi");
        Message msg3 = new Message(UUID.randomUUID(), "Ok");
        c.sendMessage(msg1);
        c.sendMessage(msg2);
        c.sendMessage(msg3);

        Message[] target = {msg1, msg2, msg3};
        assertArrayEquals(c.getMessages(), target);
    }

    @Test
    public void testAddMember() {
        Conversation c = new Conversation();
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        UUID[] target = {id1, id2};

        c.addMember(id1);
        c.addMember(id2);
        assertArrayEquals(c.getMembers(), target);
    }

    @Test
    public void testRemoveMember() {
        Conversation c = new Conversation();
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        UUID id3 = UUID.randomUUID();
        UUID[] target = {id1, id3};

        c.addMember(id1);
        c.addMember(id2);
        c.addMember(id3);
        c.removeMember(id2);

        assertArrayEquals(c.getMembers(), target);
    }
}