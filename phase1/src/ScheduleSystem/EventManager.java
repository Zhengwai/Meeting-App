package ScheduleSystem;

import conference_entities.Room;
import users.User;

import java.io.*;
import java.util.*;

/**
 * Use case class for Event class.
 */
public class EventManager {
    private ArrayList<Event> events;
    private ArrayList<Room> rooms;
    public EventManager(){
        this.events = deserializeEvents();
        this.rooms = deserializeRooms();
    }

    /**
     * Private method that deserializes the event information.
     * @return serialized event information
     */

    private ArrayList<Event> deserializeEvents() {
        try {
            FileInputStream fileInputStream = new FileInputStream("phase1/src/ScheduleSystem/Events");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            ArrayList<Event> temp = (ArrayList<Event>) objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();
            return temp;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Private method that deserializes the room information.
     * @return serialized room information
     */
    private ArrayList<Room> deserializeRooms() {
        try {
            FileInputStream fileInputStream = new FileInputStream("phase1/src/conference_entities/Rooms");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            ArrayList<Room> temp = (ArrayList<Room>) objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();
            return temp;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Private method serializes the room information in the system.
     */
    private void serializeEvents(){
        try{
            FileOutputStream fileOutputStream = new FileOutputStream("phase1/src/ScheduleSystem/Events");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(events);

            objectOutputStream.close();
            fileOutputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Private method which serializes the room information in the system.
     */
    private void serializeRooms(){
        try{
            FileOutputStream fileOutputStream = new FileOutputStream("phase1/src/conference_entities/Rooms");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(rooms);

            objectOutputStream.close();
            fileOutputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Attempt to add an event to the system
     * @param event event which we are adding
     * @return true if and only if the event was succesfully added to the system
     */
    public boolean addEvent(Event event){
        if (this.events.contains(event)){
            return false;
        }
        else{
            this.events.add(event);
            serializeEvents();
            return true;
        }
    }

    /**
     * Add an room to the event location
     * @param room room object we are adding to the system
     * @return true if and only if a room was successfully added
     */
    public boolean addRoom(Room room){
        if (this.rooms.contains(room)){
            return false;
        }
        this.rooms.add(room);
        serializeRooms();
        return true;
        }

    /**
     * returns the event with the specified eventID
     * @param eventID ID of the event we are searching up
     * @return the event with the appropriate eventID
     * @throws NoEventFoundException
     */
    public Event getEventByID(UUID eventID) throws NoEventFoundException{
        for (Event e:events){
            if (e.getId() == eventID){
                return e;
            }
        }
        throw new NoEventFoundException();
    }
    public ArrayList<Event> getEvents(){
        return this.events;
    }

    /**
     * Attempt to sign up a user to an event
     * @param user User whom we are signing up
     * @param event the event which we are signing the user up to
     * @return true if and only if a user was signed up successfully
     */
    public boolean signUpUser(User user, Event event){
        for (UUID i : user.getEnrolledEvents()){
            try {
                Event ev = this.getEventByID(i);
                if (ev.getDate() == event.getDate()){
                    return false;
                }
            } catch (NoEventFoundException e) {
                e.printStackTrace();
                return false;
            }
        }
        event.addAttendee(user.getID());
        user.addEvent(event.getId());
        return true;
        }

    /**
     * Attempt to remove user signed up in an Event
     * @param user user whom we are removing
     * @param event event we are removing the user from
     * @return true if and only if an user was successfully removed from an event
     */
    public boolean removeUser(User user, Event event){
        if (event.getAttendees().contains(user)){
            event.getAttendees().remove(user);
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Method returns all the events an individual is sign up for
     * @param user User whose events we are searching
     * @return an array list of the events the user is signed up for
     */
    public ArrayList<Event> getEventsByUser(User user){
        ArrayList<Event> userEvents = new ArrayList<>();
        for (Event e:events){
            if (e.getAttendees().contains(user.getID())){
                userEvents.add(e);
            }
        }
        return userEvents;
    }
}
class NoEventFoundException extends Exception {
}