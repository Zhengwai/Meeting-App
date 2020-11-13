import message_system.Message;
import message_system.MessageManager;
import message_system.Reply;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Calendar;
import java.util.UUID;

import static org.junit.Assert.*;

public class MessageManagerTest {

    @Test(timeout = 150)
    public void testNewConversation() {
        MessageManager mm = new MessageManager();
        UUID conId = mm.newConversation();
        UUID dummyID = UUID.fromString("3e2b900c-1e3a-11eb-adc1-0242ac120002");
        Message[] target = { new Message(dummyID, "Hi")};

        mm.sendMessage(conId, target[0]);
        assertArrayEquals(mm.getMessages(conId), target);
    }

    @Test(timeout = 150)
    public void testAddConversation() {
        MessageManager mm1 = new MessageManager();
        MessageManager mm2 = new MessageManager();
        UUID conId = mm1.newConversation();
        UUID dummyID = UUID.fromString("3e2b900c-1e3a-11eb-adc1-0242ac120002");

        Message[] target = { new Message(dummyID, "Hi")};

        mm2.addConversation(conId);
        mm2.sendMessage(conId, target[0]);
        assertArrayEquals(mm2.getMessages(conId), target);
    }

    @Test(timeout = 150)
    public void testSendMessages() {
        MessageManager mm = new MessageManager();
        UUID conId = mm.newConversation();
        UUID dummyID1 = UUID.fromString("3e2b900c-1e3a-11eb-adc1-0242ac120001");
        UUID dummyID2 = UUID.fromString("3e2b900c-1e3a-11eb-adc1-0242ac120002");

        Message[] target = { new Message(dummyID1, "Hi!"), new Message(dummyID2, "Nice to meet you :)"),
                new Message(dummyID1, "Goodbye!"), new Reply(dummyID2, ":(", dummyID1 ) };

        for (Message msg: target) {
            mm.sendMessage(conId, msg);
        }

        assertArrayEquals(mm.getMessages(conId), target);

    }

}