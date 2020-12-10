package database;

import Repository.EventData;
import entities.Event;
import gateways.EventDataGateway;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class EventDataMapper implements EventDataGateway {
    private Database db;

    public EventDataMapper() {
        db = new Database();
    }

    @Override
    public void insertEvent(Event evt) {
        try {
            Date startTime = Date.from(evt.getStartTime().atZone(ZoneId.systemDefault()).toInstant());
            Date endTime = Date.from(evt.getEndTime().atZone(ZoneId.systemDefault()).toInstant());
            java.sql.Date sqlStartTime = java.sql.Date.valueOf(String.valueOf(startTime));
            java.sql.Date sqlEndTime = java.sql.Date.valueOf(String.valueOf(endTime));

            db.insertNewEvent(evt.getId(), evt.getName().toString(), evt.getDescription(), sqlStartTime,
                    sqlEndTime, evt.getCapacity());
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
            Date startTime = Date.from(evt.getStartTime().atZone(ZoneId.systemDefault()).toInstant());
            Date endTime = Date.from(evt.getEndTime().atZone(ZoneId.systemDefault()).toInstant());
            db.updateEventTime(evt.getId(), java.sql.Date.valueOf(String.valueOf(startTime)), java.sql.Date.valueOf(String.valueOf(endTime)));
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

        // TODO: Needs to be implemented
        // Message Data mapper needs further testing
        // Message + Conversation need setters for their IDs
        // Event Data mapper needs testing when all done
        // Need table for requests (or column in users table)
        // Refactor DB code (organize by subsystem with Facade)
        // Storing Archived and Unread messages

        return new ArrayList<>();
    }
}
