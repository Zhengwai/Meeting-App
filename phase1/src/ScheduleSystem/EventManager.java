package ScheduleSystem;

import users.User;
import users.Speaker;
import users.UserGateway;
import users.UserManager;

import java.io.*;
import java.util.*;

public class EventManager implements Serializable{
    private final Event notFoundEvent = new Event();
    private final Room notFoundRoom = new Room();
    private ArrayList<Event> events = new ArrayList<>();
    private ArrayList<Room> rooms = new ArrayList<>();

    public EventManager() throws ClassNotFoundException {
    }

    public boolean addEvent(Event event) throws IOException {
        if (this.events.contains(event)) {
            return false;
        } else {
            this.events.add(event);
            return true;
        }
    }


    public boolean addRoom(Room room) throws IOException {
        if (this.rooms.contains(room)) {
            return false;
        }
        this.rooms.add(room);
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
            if (r.getRoomName().toUpperCase().equals(name.toUpperCase())){
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

    public boolean removeUser(User user, Event event) throws UnableToCancelException, IOException {
        if (event.getAttendees().contains(user.getID())) {
            event.removeAttendee(user.getID());
            return true;
        } else {
            throw new UnableToCancelException();
        }

    }

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

    public ArrayList<Event> getEventsBySpeaker(Speaker user) {
        ArrayList<Event> speakerEvents = new ArrayList<>();
        for (UUID id : user.getSpeakerEvents()) {
            speakerEvents.add(getEventByID(id));
        }
        return speakerEvents;
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
        for (Event e:this.events){
            if (e.hasSpace()){
                if(!e.hasAttendee(user.getID())){
                    events.add(e);
                }
            }
        }
        return events;
    }

    public boolean roomAvailableForEvent(Room room, Event event){
        for (UUID eventID : room.getEvents()) {
            if (getEventByID(eventID).getDate().equals(event.getDate())) {
                return false;
            }
        }
        return true;
    }

    public void assignRoom(Room room, Event event) throws IOException {
        room.addEvent(event.getId());
        event.setRoom(room.getID());
    }

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
