package database;

import entities.Room;
import gateways.RoomDataGateway;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Database interactor class.
 * Responsible for mapping instances of rooms to the database and vice versa.
 */
public class RoomDataMapper implements RoomDataGateway {
    private Database db = new Database();

    @Override
    public ArrayList<Room> fetchRooms() {
        try {
            ResultSet rs = db.getAllFromTable("rooms");
            ArrayList<Room> out = new ArrayList<>();

            while (rs.next()) {
                Room room = new Room(rs.getInt("capacity"), rs.getString("name"));
                room.setID(UUID.fromString(rs.getString("uuid")));

                String[] eventsList = db.parseArrayList((String) rs.getObject("events"));
                if (eventsList != null) {
                    for (String s: eventsList) {
                        room.addEvent(UUID.fromString(s));
                    }
                }

                out.add(room);

            }

            return out;
        } catch (SQLException e) {
            System.out.println("Something went wrong trying to get all rooms.");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void insertRoom(Room room) {
        try {
            db.insertRoom(room.getID(), room.getRoomName(), room.getCapacity());
        } catch (SQLException e) {
            System.out.println("Something went wrong trying to insert that room.");
            e.printStackTrace();
        }
    }

    @Override
    public void updateRoomEvents(Room room) {
        try {
            db.updateTableRowValue("rooms", "events", room.getID(), room.getEvents());
        } catch (SQLException e) {
            System.out.println("Something went wrong trying to update that room.");
            e.printStackTrace();
        }
    }
}
