package ScheduleSystem;

import users.Speaker;
import users.User;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Date;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.UUID;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

/**
 * The event entity is a class responsible for the events in the convention. Each instance of an event models a
 * separate event.
 */
public class Event implements Serializable {
    private UUID id;
    private String name;
    private Date date;
    private int capacity;
    private ArrayList<UUID> attendees;
    private UUID speaker;
    private UUID room;

    /**
     * @param name the name of the event
     * @param date the time and date of the event
     * @param capacity the maximum attendee capacity of the event
     */
    public Event(String name, Date date, int capacity){
        this.name = name.toUpperCase();
        this.date = date;
        this.capacity = capacity;
        this.attendees = new ArrayList<>();
        this.id = UUID.randomUUID();
    }

    /**
     * Second constructor for event without for case there is no event information.
     */
    public Event() {
        this.id = UUID.randomUUID();
    }

    /**
     * @return the id of the event
     */
    public UUID getId(){
        return this.id;
    }

    /**
     * @return the name of the event
     */
    public String getName(){
        return this.name;
    }

    /**
     * @return date of the event as a date object
     */
    public Date getDate(){
        return date;
    }

    /**
     * @return the the maximum capacity of the event
     */
    public int getCapacity(){
        return this.capacity;
    }

    /**
     * @return the current amount of attendees registered for the event
     */
    public int currentNum(){
        return attendees.size();
    }

    /**
     * @return an ArrayList of the attendees
     */
    public ArrayList<UUID> getAttendees() {
        return attendees;
    }

    /**
     * Add a designated speaker for the event
     * @param id The id of the speaker
     */
    public void addSpeaker(UUID id){
        speaker = id;
    }

    /**
     * @return true if and only if the event has a designated speaker
     */
    public boolean existsSpeaker(){
        return speaker != null;
    }

    /**
     * remove the speaker of the event
     */
    public void removeSpeaker(){
        speaker = null;
    }

    /**
     * @return the speaker of the event if it exists. Null otherwise
     */
    public UUID getSpeaker(){
        if (this.existsSpeaker()){
            return speaker;
        }
        return null;
    }

    /**
     * @return the assigned room of the event if it exists. Null otherwise.
     */
    public UUID getRoom(){
        if (this.assignedRoom()){
            return room;
        }
        return null;
    }

    /**
     * add an individual that will attend the event
     * @param userID the user id of the attendee
     * @return true if and only if the attendee was successfully added (the attendee was not already registered)
     */
    public boolean addAttendee(UUID userID){
        if (attendees.contains(userID)){
            return false;
        }
        attendees.add(userID);
        return true;
    }

    /**
     * check the event if an attendee with a specified user id is expected to attend the event
     * @param userID the user id of the attendee
     * @return true if and only if the attendee with the specified user id is attending the event
     */
    public boolean hasAttendee(UUID userID){
        return attendees.contains(userID);
    }

    /**
     * attempt to remove an attendee with a specified user id from the event
     * @param userID the user id of the specified attendee
     * @return true if and only if the user with the specified user id is removed from the event
     */
    public boolean removeAttendee(UUID userID){
        if (attendees.contains(userID)){
            attendees.remove(userID);
            return true;
        }
        return false;
    }

    /**
     * @return true if and only if the event is not at max capacity
     */
    public boolean hasSpace(){
        return this.currentNum() < this.capacity;
    }

    /**
     * @return true if and only if the event has an assigned room
     */
    public boolean assignedRoom(){
        return room != null;
    }

    /**
     * set the room of the event to the specified room
     * @param room the room to set the event location to
     */
    public void setRoom(UUID room) {
        this.room = room;
    }

    /**
     * remove the room assigned to the event if it exists so that the event has no specified room
     */
    public void removeRoom(){
        room = null;
    }

    @Override
    public String toString() {
        String full;
        if (currentNum() < capacity) {
            full = "available";
        } else {
            full = "full";
        }

        return name+"@"+ date.toString() +", status: "+ attendees.size() + "/" + capacity + " " + full;
    }
}
