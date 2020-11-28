package entities;

import org.junit.Test;

import java.util.UUID;

class ConversationTest {

    @Test
    void testAddAndGetMessageIDs() {
        Conversation c = new Conversation();
        UUID dummyID1 = UUID.fromString("212acdde-31cb-11eb-adc1-0242ac120002");
        UUID dummyID2 = UUID.fromString("212acdde-31cb-11eb-adc1-0242ac120003");
    }

    @Test
    void testAddAndGetMembers() {
    }


    @Test
    void testRemoveMembers() {
    }
}