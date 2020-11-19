package ScheduleSystem;

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
    private boolean hasSpeaker = false;

    /**
     * @param name the name of the event
     * @param date the time and date of the event
     * @param capacity the maximum attendee capacity of the event
     */
    public Event(String name, Date date, int capacity){
        this.name = name;
        this.date = date;
        this.capacity = capacity;
        this.attendees = new ArrayList<>();
        this.id = UUID.randomUUID();
    }

    /**
     * @return the name of the given event
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
    public int currentNum(){return attendees.size(); }
    public ArrayList<UUID> getAttendees() {
        return attendees;
    }

    /**
     * this methods attempts to add a user to the given event
     * @param userID the userID of the individual we are enrolling in the event
     * @return true if and only if the individual was successfully added to the event
     */
    public boolean addAttendee(UUID userID){
        if (this.attendees.contains(userID)){
            return false;
        }
        else {
            this.attendees.add(userID);
            return true;
        }
    }

    /**
     * @return the id of the event
     */
    public UUID getId(){
        return this.id;
    }

    /**
     * @return true if and only if the event has an assigned speaker.
     * @implNote speaker is not year implemented.
     */
    public boolean existSpeaker(){
        return hasSpeaker;
    }

    /**
     * @return a string representation of the event
     */
    @Override
    public String toString(){
        String status;
        if (currentNum() < capacity){
            status = "available";
        }
        else {
            status = "full";
        }
        return "name"+"@"+ date.toString() +", status: " + status;
    }
}
