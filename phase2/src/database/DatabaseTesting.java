package database;

import Repository.EventData;
import entities.Conversation;
import entities.Event;
import entities.Message;
import gateways.EventDataGateway;
import gateways.MessageDataGateway;

import java.time.LocalDateTime;
import java.util.UUID;

public class DatabaseTesting {
    public static void main(String[] args) {
        EventDataGateway edg = new EventDataMapper();
        UUID dummyID = UUID.fromString("f4970970-3af5-11eb-adc1-0242ac120002");
        UUID dummyID2 = UUID.fromString("f4970970-3af5-11eb-adc1-0242ac120003");
        Event evt = new Event();
        evt.setId(dummyID);
        evt.setStartTime(LocalDateTime.now());
        edg.insertEvent(evt);

        for (Event e : edg.getAllEventsFromDB()) {

        }
    }
}
