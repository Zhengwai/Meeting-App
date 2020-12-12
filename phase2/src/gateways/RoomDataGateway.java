package gateways;

import entities.Room;

import java.util.ArrayList;

/**
 * Gateway class.
 * Allows for communication between the EventManager and the database while still adhering to clean architecture.
 */
public interface RoomDataGateway {
    /**
     * Gets every Room stored in the database.
     * @return ArrayList of all rooms.
     */
    ArrayList<Room> fetchRooms();

    /**
     * Adds a new Room to the database. This method assumes this room is not already saved.
     * @param room The room to be inserted.
     */
    void insertRoom(Room room);

    /**
     * Updates the events field of a Room. This method assumes the room is in the database.
     * @param room The room to be updated.
     */
    void updateRoomEvents(Room room);
}
