package ScheduleSystem;

import users.User;
import users.Speaker;
import users.UserGateway;
import users.UserManager;

import java.io.*;
import java.util.*;
/**
 * A use case class that manages the events.
 */
public class EventManager implements Serializable{
    private final Event notFoundEvent = new Event();
    private final Room notFoundRoom = new Room();
    private ArrayList<Event> events = new ArrayList<>();
    private ArrayList<Room> rooms = new ArrayList<>();

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
        return notFoundEvent;
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
     * Sign a user up for an event
     * @param user the user to sign up
     * @param event the event to sign the user up
     * @return true iff the event is successfully signed up for the user.
     * @throws AlreadySignedUpException if the user has already signed up for the event
     * @throws TimeConflictException if the user has signed up for another event at the same time period.
     */
    public boolean signUpUser(User user, Event event) throws AlreadySignedUpException, TimeConflictException{
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
     * Removes a user from the event.
     * @param user the user to be removed.
     * @param event the event to remove from.
     * @return true iff the event has been successfully removed the user.
     * @throws UnableToCancelException when the user did not sign up for the event.
     */
    public boolean removeUser(User user, Event event) throws UnableToCancelException, IOException {
        if (event.getAttendees().contains(user.getID())) {
            event.removeAttendee(user.getID());
            return true;
        } else {
            throw new UnableToCancelException();
        }

    }
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
        return notFoundEvent;
    }
    /**
     * Returns if the event given by the name exists.
     * @param name name to search for.
     * @return true when the event exists and false otherwise.
     */
    public boolean hasEvent(String name){
        if (getEventByName(name) == notFoundEvent){
            return false;
        }
        else{
            return true;
        }
    }
    /**
     * Returns if the event given by the id exists.
     * @param id id to search for.
     * @return true when the event exists and false otherwise.
     */
    public boolean hasEvent(UUID id){
        if (getEventByID(id) == notFoundEvent){
            return false;
        }
        else{
            return true;
        }
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
            if (getEventByID(eventID).getDate().equals(event.getDate())) {
                return false;
            }
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
                if (getEventByID(id).getDate().equals(event.getDate())) {
                    return false;
                }
            }
        }
        return true;
    }
}
