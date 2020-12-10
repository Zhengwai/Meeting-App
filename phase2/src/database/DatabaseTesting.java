package database;

import entities.Message;
import gateways.MessageDataGateway;
import java.util.UUID;

public class DatabaseTesting {
    public static void main(String[] args) {
        MessageDataGateway mdg = new MessageDataMapper();
        UUID dummyID = UUID.fromString("f4970970-3af5-11eb-adc1-0242ac120002");
        Message msg = new Message(dummyID, "Hello!");
        System.out.println(msg.getTimeSent());
        mdg.insertNewMessage(msg);
        for (Message m : mdg.fetchMessages()) {
            System.out.println(m.getMessageID() + " " + m.getSenderID() + " " + m.getBody() + " " + m.getTimeSent());
        }
    }
}
