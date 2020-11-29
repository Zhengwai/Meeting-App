package entities;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class ConversationTest {
    @Test
    public void testAddAndGetMessageIDs() {
        Conversation c = new Conversation();
        UUID dummyID1 = UUID.fromString("212acdde-31cb-11eb-adc1-0242ac120002");
        UUID dummyID2 = UUID.fromString("212acdde-31cb-11eb-adc1-0242ac120003");
        c.addMessageID(dummyID1);
        c.addMessageID(dummyID2);

        assert dummyID1 == c.getMessageIDs().get(0);
        assert dummyID2 == c.getMessageIDs().get(1);
    }

    @Test
    public void testAddAndGetMembers() {
        Conversation c = new Conversation();
        UUID dummyID1 = UUID.fromString("212acdde-31cb-11eb-adc1-0242ac120002");
        UUID dummyID2 = UUID.fromString("212acdde-31cb-11eb-adc1-0242ac120003");
        c.addMember(dummyID1);
        c.addMember(dummyID2);

        assert dummyID1 == c.getMembers().get(0);
        assert dummyID2 == c.getMembers().get(1);
    }


    @Test
    public void testRemoveMembers() {
        Conversation c = new Conversation();
        UUID dummyID1 = UUID.fromString("212acdde-31cb-11eb-adc1-0242ac120002");
        UUID dummyID2 = UUID.fromString("212acdde-31cb-11eb-adc1-0242ac120003");
        UUID dummyID3 = UUID.fromString("212acdde-31cb-11eb-adc1-0242ac120004");
        c.addMember(dummyID1);
        c.addMember(dummyID2);
        c.removeMember(dummyID1);

        assert c.getMembers().size() == 1;
        assert c.getMembers().get(0) == dummyID2;

        c.addMember(dummyID3);

        assert  c.getMembers().get(1) == dummyID3;

        c.removeMember(dummyID3);

        assert c.getMembers().size() == 1;
        assert c.getMembers().get(0) == dummyID2;
    }
}