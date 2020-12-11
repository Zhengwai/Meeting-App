package database;

import Repository.EventData;
import entities.*;
import gateways.EventDataGateway;
import gateways.MessageDataGateway;
import gateways.RoomDataGateway;

import java.time.LocalDateTime;
import java.util.UUID;

public class DatabaseTesting {
    public static void main(String[] args) {
        RoomDataGateway rdg = new RoomDataMapper();
        Room room = new Room(10, "test");
        UUID dummyID = UUID.fromString("3691bcd0-b86b-4c04-ba94-816aa6f7b822");

        rdg.insertRoom(room);

        room.addEvent(dummyID);
        rdg.updateRoomEvents(room);

        for (Room r: rdg.fetchRooms()) {
            System.out.println(r.getID() + " " + r.getCapacity() + " " + r.getRoomName() + " " + r.getEvents());
        }
    }
}
