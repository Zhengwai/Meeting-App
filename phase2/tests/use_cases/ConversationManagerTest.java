package use_cases;

import entities.Conversation;
import entities.Message;
import org.junit.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.*;

public class ConversationManagerTest {

    @Test
    public void newConversation() {
        ConversationManager cm = new ConversationManager();
        UUID conID = cm.newConversation();
        assert cm.getConversation(conID) != null;

        UUID conID2 = cm.newConversation();
        ArrayList<UUID> ids = new ArrayList<>();
        ids.add(conID);
        ids.add(conID2);

        //assert cm.getConversations(ids).get(0) == cm.getConversation(conID);
        assert cm.getConversation(conID).getID() == conID;
        //assert cm.getConversations(ids).get(1) == cm.getConversation(conID2);
        assert cm.getConversation(conID2).getID() == conID2;
    }

    @Test
    public void testSendAndGetMessageInConversation() {
        ConversationManager cm = new ConversationManager();
        UUID conID = cm.newConversation();
        UUID dummyID1 = UUID.fromString("212acdde-31cb-11eb-adc1-0242ac120002");
        UUID dummyID2 = UUID.fromString("212acdde-31cb-11eb-adc1-0242ac120003");

        cm.sendMessageInConversation(conID, dummyID1, "Hello!");
        cm.sendMessageInConversation(conID, dummyID2, "Hi!");

       // assert cm.getMessagesInConversation(conID).get(0).getSenderID() == dummyID1;
       // assert cm.getMessagesInConversation(conID).get(1).getBody().equals("Hi!");
    }


    @Test
    public void addUserToConversation() {
        ConversationManager cm = new ConversationManager();
        UUID conID = cm.newConversation();
        UUID dummyID1 = UUID.fromString("212acdde-31cb-11eb-adc1-0242ac120002");
        UUID dummyID2 = UUID.fromString("212acdde-31cb-11eb-adc1-0242ac120003");
        cm.addUserToConversation(conID, dummyID1);

        assert cm.getMemberIDsInConversation(conID).get(0) == dummyID1;
        assert cm.getMemberIDsInConversation(conID).size() == 1;

        cm.addUserToConversation(conID, dummyID2);

        assert cm.getMemberIDsInConversation(conID).get(1) == dummyID2;
        assert cm.getMemberIDsInConversation(conID).size() == 2;
    }

    @Test
    public void testUserInConversation() {
        ConversationManager cm = new ConversationManager();
        UUID conID1 = cm.newConversation();
        UUID conID2 = cm.newConversation();
        UUID dummyID1 = UUID.fromString("212acdde-31cb-11eb-adc1-0242ac120002");
        UUID dummyID2 = UUID.fromString("212acdde-31cb-11eb-adc1-0242ac120003");
        UUID dummyID3 = UUID.fromString("212acdde-31cb-11eb-adc1-0242ac120004");

        cm.addUserToConversation(conID1, dummyID1);
        cm.addUserToConversation(conID1, dummyID2);
        cm.addUserToConversation(conID2, dummyID2);
        cm.addUserToConversation(conID2, dummyID3);

        assert cm.userInConversation(dummyID1, conID1);
        assert cm.userInConversation(dummyID2, conID1);
        assert !cm.userInConversation(dummyID3, conID1);

        assert !cm.userInConversation(dummyID1, conID2);
        assert cm.userInConversation(dummyID2, conID2);
        assert cm.userInConversation(dummyID3, conID2);
    }

    @Test
    public void getUserConversations() {
        ConversationManager cm = new ConversationManager();
        UUID conID1 = cm.newConversation();
        UUID conID2 = cm.newConversation();
        UUID dummyID1 = UUID.fromString("212acdde-31cb-11eb-adc1-0242ac120002");
        UUID dummyID2 = UUID.fromString("212acdde-31cb-11eb-adc1-0242ac120003");
        UUID dummyID3 = UUID.fromString("212acdde-31cb-11eb-adc1-0242ac120004");

        cm.addUserToConversation(conID1, dummyID1);
        cm.addUserToConversation(conID1, dummyID2);
        cm.addUserToConversation(conID2, dummyID2);
        cm.addUserToConversation(conID2, dummyID3);

        ArrayList<Conversation> target1 = new ArrayList<>();
        target1.add(cm.getConversation(conID1));

        ArrayList<Conversation> target2 = new ArrayList<>();
        target2.add(cm.getConversation(conID1));
        target2.add(cm.getConversation(conID2));

        ArrayList<Conversation> target3 = new ArrayList<>();
        target3.add(cm.getConversation(conID2));

        assert cm.getUserConversations(dummyID1).equals(target1);

        // cursed assertion???
        assert cm.getUserConversations(dummyID2).equals(target2);

        assert cm.getUserConversations(dummyID3).equals(target3);
    }
}