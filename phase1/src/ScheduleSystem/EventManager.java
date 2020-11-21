package ScheduleSystem;

import users.User;
import users.Speaker;
import users.UserGateway;
import users.UserManager;

import java.io.*;
import java.util.*;

/**
 * Use case class for event entity
 */
public class EventManager implements Serializable{
    private final Event notFoundEvent = new Event();
    private final Room notFoundRoom = new Room();
    private ArrayList<Event> events = new ArrayList<>();
    private ArrayList<Room> rooms = new ArrayList<>();

    public EventManager() {
    }

    /**
     * Add an event to the Schedule system
     * @param event the event being added to the system
     * @return true if and only if the event was successfully added
     */
    public boolean addEvent(Event event) {
        if (this.events.contains(event)) {
            return false;
        } else {
            this.events.add(event);
            return true;
        }
    }

    /**
     * add empty room to the system
     * @param room the room being added to the system
     * @return true if and only if a room was successfully added that did not exist before
     */
    public boolean addRoom(Room room) {
        if (this.rooms.contains(room)) {
            return false;
        }
        this.rooms.add(room);
        return true;
    }

    /**
     * Search the system for an event by the ID of the event.
     * @param eventID the ID of the event we are searching for
     * @return the event if it exits.
     */
    public Event getEventByID(UUID eventID) {
        for (Event e : events) {
            if (e.getId() == eventID) {
                return e;
            }
        }
        return notFoundEvent;
    }

    /**
     * Search the system for an room by the ID of the room
     * @param roomID the ID of the room we are searching for
     * @return the event if it exists
     */
    public Room getRoomByID(UUID roomID) {
        for (Room r : rooms) {
            if (r.getID() == roomID) {
                return r;
            }
        }
        return notFoundRoom;
    }

    /**
     * Search the system for an room by the name of the room
     * @param name the name of the room we are searching for
     * @return the room if it exists
     */
    public Room getRoomByName(String name){
        for (Room r:rooms){
            if (r.getRoomName().toUpperCase().equals(name.toUpperCase())){
                return r;
            }
        }
        return notFoundRoom;
    }

    /**
     * @return an ArrayList of the events registered in the system
     */
    public ArrayList<Event> getEvents() {
        return this.events;
    }

    /**
     * @return and ArrayList of the rooms registered in the system
     */
    public ArrayList<Room> getRooms(){
        return this.rooms;
    }

    /**
     * Sign up a user to an already existing event.
     * @param user the user we are signing up
     * @param event the event which we are attempting to sign to user up to
     * @return true if and only if the user was successfully signed up for the event
     */
    public boolean signUpUser(User user, Event event) throws AlreadySignedUpException, TimeConflictException {
        for (Event i : getEventsByUser(user)) {
            if (event.getId().equals(i.getId())) {
                throw new AlreadySignedUpException();
            }
            if (i.getDate().toString().equals(event.getDate().toString())){
                throw new TimeConflictException();
            }
        }
        if(user.getType().equals("s")){
            event.setSpeaker(user.getID());
            return true;
        }

        else if (user.getType().equals("o") | user.getType().equals("a")) {
            event.addAttendee(user.getID());
            return true;
        }

        return false;
    }

    /**
     * Remove an user from an event that they are signed up for
     * @param user the user whom we are removing
     * @param event the event which we are removing the user from
     * @return true if and only if the user was successfully removed from the event
     */
    public boolean removeUser(User user, Event event) throws UnableToCancelException {
        if (event.getAttendees().contains(user.getID())) {
            event.removeAttendee(user.getID());
            return true;
        } else {
            throw new UnableToCancelException();
        }

    }

    /**
     * Search the system for events the user is a participant in (whether attendee or speaker)
     * @param user the user whom we are searching
     * @return an ArrayList of events the user is a participant in
     */
    public ArrayList<Event> getEventsByUser(User user) {
        ArrayList<Event> userEvents = new ArrayList<>();
        for (Event e : events) {
            if (e.existsSpeaker()) {
                if (e.getAttendees().contains(user.getID()) | e.getSpeaker().equals(user.getID())) {
                    userEvents.add(e);
                }
            } else {
                if (e.getAttendees().contains(user.getID())) {
                    userEvents.add(e);
                }
            }
        }
        return userEvents;
    }

    /**
     * Search the system for events which the user is a speaker for
     * @param user the user whom we are searching for
     * @return an ArrayList of events the user if a speaker for
     */
    public ArrayList<Event> getEventsBySpeaker(Speaker user) {
        ArrayList<Event> speakerEvents = new ArrayList<>();
        for (UUID id : user.getSpeakerEvents()) {
            speakerEvents.add(getEventByID(id));
        }
        return speakerEvents;
    }

    /**
     * Search the system for the event by the name of the event
     * @param name the name of the event we are serching for
     * @return the event with the matching name if it exists in the system
     */
    public Event getEventByName(String name){
        for (Event e:events){
            if (e.getName().equals(name.toUpperCase())){
                return e;
            }
        }
        return notFoundEvent;
    }

    /**
     * Search the system for all events which are not at full capacity and which the user is not already signed up for
     * @param user the user we are searching for available events
     * @return an ArrayList of events which are available for the user
     */
    public ArrayList<Event> getAvailableEventsForUser(User user){
        ArrayList<Event> events = new ArrayList<>();
        for (Event e:this.events){
            if (e.hasSpace()){
                if(!e.hasAttendee(user.getID())){
                    events.add(e);
                }
            }
        }
        return events;
    }

    /**
     * Checks if a room in the system is empty during the duration of an event
     * @param room the room which we are checking availability for
     * @param event the event whose time is when we are checking if the room is empty
     * @return true if and only if the room is empty during the entire duration of the event
     */
    public boolean roomAvailableForEvent(Room room, Event event){
        for (UUID eventID : room.getEvents()) {
            if (getEventByID(eventID).getDate().equals(event.getDate())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Assign a room to an event or change the room of the event if the event is already assigned a room.
     * @param room the room we which we are assigning for the event
     * @param event the event which we are assigning the room for
     */
    public void assignRoom(Room room, Event event) {
        room.addEvent(event.getId());
        event.setRoom(room.getID());
    }

    /**
     * Checks an event if it has a time conflict with any event the user is already a participant in.
     * @param user the user which we are checking for availability during the event
     * @param event the event which we are checking for time conflicts
     * @return true if and only if the user has no time conflicts for the entire duration of the event
     */
    public boolean userAvailableForEvent(User user, Event event) {
        ArrayList<UUID> enrolledEvents = user.getEnrolledEvents();
        if(enrolledEvents.size() > 0) {
            for (UUID id : enrolledEvents) {
                if (getEventByID(id).getDate().equals(event.getDate())) {
                    return false;
                }
            }
        }
        return true;
    }
}
