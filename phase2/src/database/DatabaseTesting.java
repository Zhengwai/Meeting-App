package database;

import entities.Conversation;
import entities.Message;
import gateways.MessageDataGateway;
import java.util.UUID;

public class DatabaseTesting {
    public static void main(String[] args) {
        MessageDataGateway mdg = new MessageDataMapper();
        UUID dummyID = UUID.fromString("f4970970-3af5-11eb-adc1-0242ac120002");
        UUID dummyID2 = UUID.fromString("f4970970-3af5-11eb-adc1-0242ac120003");
        Conversation con = new Conversation();
        con.addMember(dummyID);
        con.addMessageID(dummyID2);
        mdg.insertConversation(con);
        for (Conversation c : mdg.fetchConversations()) {
            System.out.println(c.getID() + " " + c.getMembers() + " " + c.getMessageIDs() + " " + c.getName());
        }
    }
}
