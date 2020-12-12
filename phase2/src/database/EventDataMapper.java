package database;

import Repository.EventData;
import entities.Event;
import entities.Party;
import entities.Seminar;
import entities.TED;
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
                    evt.getEndTime().toString(), evt.getCapacity(), evt.getRoom(), evt.getType().getValue(), evt.getVIP());
        } catch (SQLException e) {
            System.out.println("Something went wrong trying to insert that event");
            e.printStackTrace();
        }
    }

    @Override
    public void updateEventName(Event evt) {
        try {
          db.updateTableRowValue("events", "name", evt.getId(), evt.getName().getValue());
        } catch (SQLException e) {
            System.out.println("Something went wrong trying to update that event's name");
            e.printStackTrace();
        }
    }

    @Override
    public void updateEventTime(Event evt) {
        try {
            db.updateTableRowValue("events", "startTime", evt.getId(), evt.getStartTimeString().getValue());
            db.updateTableRowValue("events", "endTime", evt.getId(), evt.getEndTimeString().getValue());
        } catch (SQLException e) {
            System.out.println("Something went wrong trying to update the time of this event");
            e.printStackTrace();
        }
    }

    @Override
    public void updateEventCapacity(Event evt) {
        try {
            db.updateTableRowValue("events", "capacity", evt.getId(), evt.getCapacity());
        } catch (SQLException e) {
            System.out.println("Something went wrong trying to update the capacity of this event");
            e.printStackTrace();
        }
    }

    @Override
    public void updateEventAttendees(Event evt) {
        try {
            db.updateTableRowValue("events", "attendees", evt.getId(), evt.getAttendees());
        } catch (SQLException e) {
            System.out.println("Something went wrong trying to update the attendees in this event.");
            e.printStackTrace();
        }
    }

    @Override
    public void updateEventIsVIP(Event evt) {
        try {
            int val = 0;
            if (evt.getVIP()) val = 1;

            db.updateTableRowValue("events", "isVIP", evt.getId(), val);
        } catch (SQLException e) {
            System.out.println("Something went wrong trying to update the VIP status in this event.");
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Event> getAllEventsFromDB() {
        try {
            ResultSet rs = db.getAllFromTable("events");
            ArrayList<Event> out = new ArrayList<>();

            while (rs.next()) {
                Event e = new Event();
                String name = rs.getString("name");
                int capacity = rs.getInt("capacity");
                LocalDateTime startTime = LocalDateTime.parse(rs.getString("startTime"));
                LocalDateTime endTime = LocalDateTime.parse(rs.getString("endTime"));

                int isVIPVal = rs.getInt("isVIP");
                Boolean isVIP;

                if (isVIPVal == 0) isVIP = false;
                else isVIP = true;

                switch (rs.getString("type")) {
                    case "PARTY":
                        e = new Party(name, capacity, startTime, endTime, isVIP);
                        break;
                    case "SEMINAR":
                        e = new Seminar(name, capacity, startTime, endTime, isVIP);
                        break;
                    case "TED":
                        e = new TED(name, capacity, startTime, endTime, isVIP);
                        break;
                }

                UUID eventID = UUID.fromString(rs.getString("uuid"));
                e.setId(eventID);

                String rawAttendees = (String) rs.getObject("attendees");
                if (rawAttendees != null && !rawAttendees.equals("[]")) {
                    rawAttendees = rawAttendees.substring(1, rawAttendees.length() - 1); // Remove the "[" and "]" from string
                    String[] attendeesList = rawAttendees.split(", ");
                    for (String s: attendeesList) {
                        e.addAttendee(UUID.fromString(s));
                    }
                }

                UUID roomID = UUID.fromString(rs.getString("room"));
                e.setRoom(roomID);
                e.setDescription(rs.getString("description"));
                out.add(e);
            }

            return out;
        } catch (SQLException e) {
            System.out.println("Something went wrong with getting all events.");
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    @Override
    public void deleteEventInDB(Event evt) {
        try {
            db.deleteAnEvent(evt.getId());
        } catch (SQLException e) {
            System.out.println("Something went wrong with trying to delete that event.");
            e.printStackTrace();
        }
    }
}
