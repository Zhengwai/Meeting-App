package use_cases;

import Repository.EventData;
import entities.Room;
import entities.Event;
import entities.User;
import entities.Speaker;

import java.io.*;
import java.util.*;
/**
 * A use case class that manages the events.
 */
public class EventManager implements Serializable{
    private final Room notFoundRoom = new Room();
    private ArrayList<Event> events = new ArrayList<>();
    private ArrayList<Room> rooms = new ArrayList<>();
    private EventData eventData;

    public EventManager() throws ClassNotFoundException {
    }
    /**
     *Initializes the EventManager.
     */
    public boolean addEvent(Event event){
        if (this.events.contains(event)) {
            return false;
        } else {
            this.events.add(event);
            return true;
        }
    }

    /**
     * Adds a room to the system.
     * @param room room to be added.
     * @return true iff the room has successfully been added.
     */
    public boolean addRoom(Room room){
        if (this.rooms.contains(room)) {
            return false;
        }
        this.rooms.add(room);
        return true;
    }
    /**
     * Returns the event with associated ID.
     * @param eventID the ID to be searching for.
     * @return the event with <code>eventID</code>
     */
    public Event getEventByID(UUID eventID) {
        for (Event e : events) {
            if (e.getId() == eventID) {
                return e;
            }
        }
        return null;
    }
    /**
     * Returns the room with associated ID.
     * @param roomID the ID to be searching for.
     * @return the event with <code>roomID</code>
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
     * Returns the room with associated name.
     * @param name the room to be searching for.
     * @return the room called <code>name</code>
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
     * Returns all the events in the system.
     * @return an ArrayList of all events in the system.
     */
    public ArrayList<Event> getEvents() {
        return this.events;
    }
    /**
     * Returns all the rooms in the system.
     * @return an ArrayList of all rooms in the system.
     */
    public ArrayList<Room> getRooms(){
        return this.rooms;
    }
    /**

    public boolean removeEvent(Event event){
        if(this.events.contains(event)){
            this.events.remove(event);
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * Returns all the events a user has signed up for.
     * @param user the user whose events are to be returned.
     * @return an ArrayList of the events <code>user</code> has signed up for.
     */
    public ArrayList<Event> getEventsByUser(User user) {
        //TODO:
        return null;
    }

    /**
     * Returns all the events a speaker has been assigned to.
     * @param user the speaker.
     * @return an ArrayList of the events the speaker has been assigned to.
     */
    public ArrayList<Event> getEventsBySpeaker(Speaker user) {
        ArrayList<Event> speakerEvents = new ArrayList<>();
        for (UUID id : user.getSpeakerEvents()) {
            speakerEvents.add(getEventByID(id));
        }
        return speakerEvents;
    }
    /**
     * Returns the event with specific name.
     * @param name name to search for.
     * @return returns the first event with an associated name.
     */
    public Event getEventByName(String name){
        for (Event e:events){
            if (e.getName().equals(name.toUpperCase())){
                return e;
            }
        }
        return null;
    }
    /**
     * Returns all the events available for this user.
     * The events must not be full and the user must not have signed this event up before.
     * @param user the user to search for.
     * @return ArrayList of events this user can choose to sign up.
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
     * Checks whether a room is available for a specific event.
     * @param room the room to check.
     * @param event the event to check.
     * @return true iff the room is available for event.
     */
    public boolean roomAvailableForEvent(Room room, Event event){
        for (UUID eventID : room.getEvents()) {
            if (eventsOverlap(getEventByID(eventID),event)) {
                return false;
            }
        }
        return true;
    }

    private boolean eventsOverlap(Event e1, Event e2){
        if ((e1.getEndTime().compareTo(e2.getStartTime())) < 0 | (e1.getStartTime().compareTo(e2.getEndTime())) > 0){
            return false;
        }
        return true;
    }
    /**
     * Assign a room to a specific event.
     * @param room the room to assign.
     * @param event the event to assign.
     */
    public void assignRoom(Room room, Event event) throws IOException {
        room.addEvent(event.getId());
        event.setRoom(room.getID());
        event.setCapacity(room.getCapacity());
    }
    /**
     * Checks if a user is available for a certain event, aka no time conflict.
     * @param user the user to check.
     * @param event the event to check.
     * @return true iff the user is available to participate in the event.
     */
    public boolean userAvailableForEvent(User user, Event event) {
        ArrayList<UUID> enrolledEvents = user.getEnrolledEvents();
        if(enrolledEvents.size() > 0) {
            for (UUID id : enrolledEvents) {
                if (eventsOverlap(getEventByID(id), event)) {
                    return false;
                }
            }
        }
        return true;
    }
}
