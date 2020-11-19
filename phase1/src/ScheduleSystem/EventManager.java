package ScheduleSystem;

import users.User;
import users.UserGateway;
import users.UserManager;

import java.io.*;
import java.util.*;

public class EventManager {
    private EventGateway eg = new EventGateway();
    private Event notFoundEvent = new Event();
    private Room notFoundRoom = new Room();
    private UserManager um = new UserManager();
    private UserGateway ug = new UserGateway();
    private ArrayList<Event> events = eg.deserializeEvents("phase1/Events.ser");
    private ArrayList<Room> rooms = eg.deserializeRooms("phase1/Rooms.ser");
    public EventManager() throws ClassNotFoundException {
    }

    public boolean addEvent(Event event) throws IOException {
        if (this.events.contains(event)) {
            return false;
        } else {
            this.events.add(event);
            eg.serializeEvents("phase1/Events.ser", events);
            return true;
        }
    }

    public boolean addRoom(Room room) throws IOException {
        if (this.rooms.contains(room)) {
            return false;
        }
        this.rooms.add(room);
        eg.serializeRooms("phase1/Rooms.ser", rooms);
        return true;
    }

    public Event getEventByID(UUID eventID) {
        for (Event e : events) {
            if (e.getId() == eventID) {
                return e;
            }
        }
        return notFoundEvent;
    }

    public Room getRoomByID(UUID roomID) {
        for (Room r : rooms) {
            if (r.getID() == roomID) {
                return r;
            }
        }
        return notFoundRoom;
    }
    public Room getRoomByName(String name){
        for (Room r:rooms){
            if (r.getRoomName().equals(name.toUpperCase())){
                return r;
            }
        }
        return notFoundRoom;
    }

    public ArrayList<Event> getEvents() {
        return this.events;
    }

    public ArrayList<Room> getRooms(){
        return this.rooms;
    }
    public boolean signUpUser(User user, Event event) throws AlreadySignedUpException, TimeConflictException, IOException {
        for (UUID i : user.getEnrolledEvents()) {
            if (event.getId().equals(i)) {
                throw new AlreadySignedUpException();
            }
            Event ev = this.getEventByID(i);
            if (ev.getDate().equals(event.getDate())){
                throw new TimeConflictException();
            }

        }
        event.addAttendee(user.getID());
        eg.serializeEvents("phase1/Events.ser", events);
        return true;
    }

    public boolean removeUser(User user, Event event) throws UnableToCancelException, IOException {
        if (event.getAttendees().contains(user.getID())) {
            event.removeAttendee(user.getID());
            eg.serializeEvents("phase1/Events.ser", events);
            return true;
        } else {
            throw new UnableToCancelException();
        }

    }

    public ArrayList<Event> getEventsByUser(User user) {
        ArrayList<Event> userEvents = new ArrayList<>();
        for (Event e : events) {
            if (e.getAttendees().contains(user.getID())) {
                userEvents.add(e);
            }
        }
        return userEvents;
    }

    public Event getEventByName(String name){
        for (Event e:events){
            if (e.getName().equals(name.toUpperCase())){
                return e;
            }
        }
        return notFoundEvent;
    }

    public ArrayList<Event> getAvailableEventsForUser(User user){
        ArrayList<Event> events = new ArrayList<>();
        for (Event e:events){
            if (e.hasSpace()){
                if(!e.hasAttendee(user.getID())){
                    events.add(e);
                }
            }
        }
        return events;
    }

    public boolean roomAvailableForEvent(Room room, Event event){
        for (UUID eventID:room.getEvents()){
            if (getEventByID(eventID).getDate().equals(event.getDate())){
                return false;
            }
        }
        return true;
    }

    public void assignRoom(Room room, Event event) throws IOException {
        room.addEvent(event.getId());
        event.setRoom(room.getID());
        eg.serializeEvents("phase1/Events.ser", events);
        eg.serializeRooms("phase1/Rooms.ser", rooms);

    }


}