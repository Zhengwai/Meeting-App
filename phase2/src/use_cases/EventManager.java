package use_cases;

import database.EventDataMapper;
import database.RoomDataMapper;
import gateways.EventDataGateway;
import entities.Room;
import entities.Event;
import entities.TED;
import entities.Seminar;
import entities.User;
import entities.Speaker;
import gateways.EventGateway;
import gateways.RoomDataGateway;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.*;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
/**
 * A use case class that manages the events.
 */
public class EventManager implements Serializable{
    private final Room notFoundRoom = new Room();
    private final Event notFoundEvent = new Event();
    private ArrayList<Event> events = new ArrayList<>();
    private ArrayList<Room> rooms = new ArrayList<>();
    private EventDataGateway edg;
    private RoomDataGateway rdg;
    private EventFactory ef = new EventFactory();
    private UserManager um = new UserManager();

    public EventManager() {
        edg = new EventDataMapper();
        events = edg.getAllEventsFromDB();
        rdg = new RoomDataMapper();
        rooms = rdg.fetchRooms();

    }
    /**
     *Initializes the EventManager.
     */
    public boolean addEvent(Event event){
        if (this.events.contains(event)) {
            return false;
        } else {
            this.events.add(event);
            edg.insertEvent(event);
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
        rdg.insertRoom(room);
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

    public ArrayList<String> getAllEventNames(){
        ArrayList<String> eventNames = new ArrayList<>();
        for(Event e: getEvents()){
            eventNames.add(e.getName().getValue());
        }
        return eventNames;
    }
    public ArrayList<String> getAllRoomNames(){
        ArrayList<String> roomNames = new ArrayList<>();
        for(Room r: getRooms()){
            roomNames.add(r.getRoomName());
        }
        return roomNames;
    }
    /**
    public ArrayList<String> getAllAvailableRoomNames(Event evt){
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> allNames = getAllRoomNames();
        for(String r: allNames){
            if(roomAvailableForEvent(getRoomByName(r),evt)){
                names.add(r);
            }
        }
        return names;
    }
     */

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
        ArrayList<Event> evts = new ArrayList<>();
        for(Event e: events){
            if(e.getAttendees().contains(user)){
                evts.add(e);
            }
        }
        return evts;
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
     * Returns the names of all the events a speaker has been assigned to.
     * @param user the speaker.
     * @return an ArrayList of the event names the speaker has been assigned to.
     */
    public ArrayList<String> getEventNamesBySpeaker(Speaker user){
        ArrayList<String> speakerString = new ArrayList<>();
        ArrayList<Event> speakerEvent = getEventsBySpeaker(user);

        for(Event e: speakerEvent){
            speakerString.add(e.getName().toString());
        }

        return speakerString;
    }

    /**
     * Returns the event with specific name.
     * @param name name to search for.
     * @return returns the first event with an associated name.
     */
    public Event getEventByName(String name){
        for (Event e:events){
            if (e.getName().getValue().equals(name.toUpperCase())){
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
    public boolean hasRoom(String name){
        for(Room r: rooms){
            if(r.getRoomName().equals(name)){
                return true;
            }
        }
        return false;
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
        if(event.getCapacity()>room.getCapacity()){
            return false;
        }
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

    public Event createTempEvent(String name, int capacity, LocalDateTime start, LocalDateTime end){
        Event e = new Event(name, capacity, start, end, false);
        return e;
    }

    public ArrayList<String> getAvailableSpeakers(UUID eventID){
        Event event1 = getEventByID(eventID);
        ArrayList<Speaker> speakers = um.getAllSpeakers();
        boolean overlap = false;
        ArrayList<String> availableSpeakers = new ArrayList<>();

        for(Speaker s: speakers){
            ArrayList<Event> events = getEventsBySpeaker(s);
            for(Event e: events){
                if(eventsOverlap(event1, e)){
                    overlap = true;
                }
            }
            if(!overlap){
                availableSpeakers.add(s.getUsername());
            }
        }
        return availableSpeakers;
    }

    public UUID createAndAddEvent(String name, int capacity, LocalDateTime start, LocalDateTime end, String room, String type, String description, boolean vip) throws IOException{
        Event e = ef.getEvent(type.toUpperCase(), name, capacity, start, end, vip);
        if(!description.equals("")) {
            e.setDescription(description);
        }
        events.add(e);
        assignRoom(getRoomByName(room),e);
        edg.insertEvent(e);

        return e.getId();
    }

    public void cancelEvent(String name){
        for (Event e: events){
            if (e.getName().getValue().equals(name.toUpperCase())){
                events.remove(e);
                edg.deleteEventInDB(e);
                break;
            }
        }
    }

    public void createAndAddRoom(String name, int capacity){
        Room r = new Room(capacity,name);
        rooms.add(r);
        rdg.insertRoom(r);
    }

    public void assignSpeaker(UUID eventID, String speaker){
        Speaker sp = um.getSpeakerByName(speaker);
        if(getEventByID(eventID).getType().toString().equals("SEMINAR")){
            Seminar s = (Seminar) getEventByID(eventID);
            s.setSpeaker(sp.getID());
            sp.addSpeakingEvent(eventID);
        }else if(getEventByID(eventID).getType().equals("TED")){
            TED t = (TED) getEventByID(eventID);
            t.addSpeaker(sp.getID());
            sp.addSpeakingEvent(eventID);
        }
    }
}
