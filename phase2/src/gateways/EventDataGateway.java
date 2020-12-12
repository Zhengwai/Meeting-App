package gateways;

import entities.Event;

import java.util.ArrayList;

/**
 * Gateway class.
 * Allows for communication between the EventManager and the database while still adhering to clean architecture.
 */
public interface EventDataGateway {
    /**
     * Inserts a new event into the database. Assumes the ID, name, room and capacity are all not null.
     * Also assumes this event is not already in the database.
     * @param evt The event to be inserted.
     */
    void insertEvent(Event evt);

    /**
     * Updates the name of a given event already in the database.
     * @param evt The event being updated.
     */
    void updateEventName(Event evt);

    /**
     * Updates the startTime and endTime of a given event already in the database.
     * @param evt The event being updated.
     */
    void updateEventTime(Event evt);

    /**
     * Updates the Capacity field of a given event already in the database.
     * @param evt The event being updated.
     */
    void updateEventCapacity(Event evt);

    /**
     * Updates the Attendees field of a given event already in the database.
     * @param evt The event being updated.
     */
    void updateEventAttendees(Event evt);

    /**
     * Updates the isVIP field of a given event already in the database.
     * @param evt The event being updated.
     */
    void updateEventIsVIP(Event evt);

    /**
     * @return ArrayList of all events.
     */
    ArrayList<Event> getAllEventsFromDB();

    /**
     * Deletes an event from the database.
     * @param evt The event to be removed.
     */
    void deleteEventInDB(Event evt);
}
