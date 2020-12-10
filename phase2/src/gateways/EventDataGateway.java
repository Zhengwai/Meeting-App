package gateways;

import entities.Event;

import java.util.ArrayList;

public interface EventDataGateway {
    void insertEvent(Event e);
    void updateEventName(Event e);
    void updateEventTime(Event e);
    void updateEventCapacity(Event e);
    void updateEventAttendees(Event e);
    ArrayList<Event> getAllEventsFromDB();
}
