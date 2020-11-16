package ScheduleSystem;

import users.User;

import java.lang.reflect.Array;
import java.util.Date;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.UUID;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
public class Event {
    private UUID id;
    private String name;
    private Date date;
    private int capacity;
    private ArrayList<UUID> attendees;
    private boolean hasSpeaker = false;

    public Event(String name, Date date, int capacity){
        this.name = name;
        this.date = date;
        this.capacity = capacity;
        this.attendees = new ArrayList<>();
        this.id = UUID.randomUUID();
    }

    public String getName(){
        return this.name;
    }
    public Date getDate(){
        return date;
    }
    public int getCapacity(){
        return this.capacity;
    }
    public int currentNum(){return attendees.size(); }
    public ArrayList<UUID> getAttendees() {
        return attendees;
    }

    public void addAttendee(UUID userID){
        this.attendees.add(userID);
    }
    public UUID getId(){ return this.id; }
    public boolean existSpeaker(){
        return hasSpeaker;
    }
}
