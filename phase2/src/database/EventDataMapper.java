package database;

import Repository.EventData;
import entities.Event;
import gateways.EventDataGateway;

import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class EventDataMapper implements EventDataGateway {
    private Database db;

    public EventDataMapper() {
        db = new Database();
    }

    @Override
    public void insertEvent(Event evt) {
        try {

            db.insertNewEvent(evt.getId(), evt.getName().getValue(), evt.getDescription(), evt.getStartTime().toString(),
                    evt.getEndTime().toString(), evt.getCapacity(), evt.getRoom());
        } catch (SQLException e) {
            System.out.println("Something went wrong trying to insert that event");
            e.printStackTrace();
        }
    }

    @Override
    public void updateEventName(Event evt) {
        try {
            db.updateEventName(evt.getId(), evt.getName().toString());
        } catch (SQLException e) {
            System.out.println("Something went wrong trying to update that event's name");
            e.printStackTrace();
        }
    }

    @Override
    public void updateEventTime(Event evt) {
        try {
            db.updateEventTime(evt.getId(), evt.getStartTime().toString(), evt.getEndTime().toString());
        } catch (SQLException e) {
            System.out.println("Something went wrong trying to update the time of this event");
            e.printStackTrace();
        }
    }

    @Override
    public void updateEventCapacity(Event evt) {
        try {
            db.updateEventCapacity(evt.getId(), evt.getCapacity());
        } catch (SQLException e) {
            System.out.println("Something went wrong trying to update the capacity of this event");
            e.printStackTrace();
        }
    }

    @Override
    public void updateEventAttendees(Event evt) {
        try {
            db.updateEventAttendees(evt.getId(), evt.getAttendees());
        } catch (SQLException e) {
            System.out.println("Something went wrong trying to update the attendees in this event.");
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Event> getAllEventsFromDB() {
        try {
            ResultSet rs = db.getAllEvents();
            ArrayList<Event> out = new ArrayList<>();

            while (rs.next()) {
                Event e = new Event();
                UUID eventID = UUID.fromString(rs.getString("uuid"));
                e.setId(eventID);
                e.setName(rs.getString("name"));
                e.setStartTime(LocalDateTime.parse(rs.getString("startTime")));
                e.setEndTime(LocalDateTime.parse(rs.getString("endTime")));

                String rawAttendees = (String) rs.getObject("attendees");
                if (rawAttendees != null) {
                    rawAttendees = rawAttendees.substring(1, rawAttendees.length() - 1); // Remove the "[" and "]" from string
                    String[] attendeesList = rawAttendees.split(", ");
                    for (String s: attendeesList) {
                        e.addAttendee(UUID.fromString(s));
                    }
                }

                UUID roomID = UUID.fromString(rs.getString("room"));
                e.setRoom(roomID);
                e.setCapacity(rs.getInt("capacity"));
                e.setDescription(rs.getString("description"));
                out.add(e);
            }

            return out;
        } catch (SQLException e) {
            System.out.println("Something went wrong with getting all events.");
        }
        // TODO: Needs to be implemented
        // Event Data mapper needs testing when all done
        // Need table for requests (or column in users table)
        // Refactor DB code (organize by subsystem with Facade)
        // Storing Archived and Unread messages

        return new ArrayList<>();
    }
}
