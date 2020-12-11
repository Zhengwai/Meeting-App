package database;

import Repository.EventData;
import entities.Attendee;
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
        Attendee attendee = new Attendee("test", "test");
        UUID dummyID2 = UUID.fromString("f4970970-3af5-11eb-adc1-0242ac120003");
        attendee.setId(dummyID2);
        UUID dummyID3 = UUID.fromString("f4970970-3af5-11eb-adc1-0242ac120004");
        Event evt = new Event();

        evt.setId(dummyID);
        evt.setName("Event name");
        evt.setCapacity(5);
        evt.setDescription("Event description");
        evt.setStartTime(LocalDateTime.now());
        evt.setEndTime(LocalDateTime.now());
        evt.addAttendee(attendee.getID());
        evt.setRoom(dummyID3);

        edg.insertEvent(evt);
        evt.addAttendee(dummyID);
        edg.updateEventAttendees(evt);

        for (Event e : edg.getAllEventsFromDB()) {
            System.out.println(e.getId() + " " + e.getName() + " " + e.getCapacity() + " " + " " + e.getDescription() +
                    e.getStartTime() + " " + e.getEndTime() + " " + e.getAttendees() + " " + e.getRoom());
        }
    }
}
