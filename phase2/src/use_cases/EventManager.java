package use_cases;

import database.EventDataMapper;
import gateways.EventDataGateway;
import entities.Room;
import entities.Event;
import entities.TED;
import entities.Seminar;
import entities.User;
import entities.Speaker;
import gateways.EventGateway;
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
    private EventFactory ef = new EventFactory();

    public EventManager() {
         edg = new EventDataMapper();
        //events = edg.getAllEventsFromDB();
        LocalDateTime t1 = LocalDateTime.now();
        LocalDateTime t2 = LocalDateTime.of(2020, Month.DECEMBER, 30, 10, 00);
        LocalDateTime t3 = LocalDateTime.of(2020, Month.DECEMBER, 30, 11, 00);
        Event e1 = new TED("test1",10,t1,t1,true);
        e1.setDescription("The first test event");
        Event e2 = new Seminar("test2",20,t1,t1, true);
        Event e3 = new TED("test3", 30,t2,t3,false);
        events.add(e1);
        events.add(e2);
        events.add(e3);
        Room r1 = new Room(10,"testRoom1");
        Room r2 = new Room(20,"testRoom2");
        Room r3 = new Room(30,"testRoom3");
        rooms.add(r1);
        rooms.add(r2);
        rooms.add(r3);
    }
    /**
     *Initializes the EventManager.
     */
    public boolean addEvent(Event event){
        if (this.events.contains(event)) {
            return false;
        } else {
            this.events.add(event);
            //edg.insertEvent(event);
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

    public Event createTempEvent(String name, int capacity, LocalDateTime start, LocalDateTime end){
        Event e = new Event(name, capacity, start, end, false);
        return e;
    }

    public void createAndAddEvent(String name, int capacity, LocalDateTime start, LocalDateTime end, String room, String type, String description, boolean vip) throws IOException{
        Event e = ef.getEvent(type.toUpperCase(), name, capacity, start, end, vip);
        /*
        if(type.equals("TED")) {
            e = new TED(name, capacity, start, end,false);
        }
        else if(type.equals("VIP")){
            e = new TED(name,capacity,start,end,true);
        }
        else if(type.equals("SEMINAR")){
            e = new Seminar(name,capacity,start,end, true);
        }
        else{
            e = new Event(name,capacity,start,end, true);
        }
         */
        if(!description.equals("")) {
            e.setDescription(description);
        }
        events.add(e);
        assignRoom(getRoomByName(room),e);
        //edg.insertEvent(e);
    }

    public void cancelEvent(String name){
        for (Event e: events){
            if (e.getName().getValue().equals(name.toUpperCase())){
                events.remove(e);
                break;
            }
        }
    }

    public void createAndAddRoom(String name, int capacity){
        Room r = new Room(capacity,name);
        rooms.add(r);
    }

}
