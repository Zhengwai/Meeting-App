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
public class Event implements Serializable {
    private UUID id;
    private String name;
    private Date date;
    private int capacity;
    private ArrayList<UUID> attendees;
    private boolean hasSpeaker = false;
    private UUID speaker;
    private UUID room;
    private boolean hasRoom = false;

    public Event(String name, Date date, int capacity){
        this.name = name.toUpperCase();
        this.date = date;
        this.capacity = capacity;
        this.attendees = new ArrayList<>();
        this.id = UUID.randomUUID();
    }

    public Event() {
        this.id = UUID.randomUUID();
    }
    public UUID getId(){ return this.id; }
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


    public void addSpeaker(UUID id){
        speaker = id;
        hasSpeaker = true;
    }
    public boolean existsSpeaker(){
        return hasSpeaker;
    }
    public void removeSpeaker(UUID id){
        speaker = null;
        hasSpeaker = false;
    }
    public UUID getSpeaker(){
        if (this.hasSpeaker){
            return speaker;
        }
        return null;
    }

    public UUID getRoom(){
        if (this.hasRoom){
            return room;
        }
        return null;
    }


    public boolean addAttendee(UUID userID){
        if (attendees.contains(userID)){
            return false;
        }
        attendees.add(userID);
        return true;
    }
    public boolean hasAttendee(UUID userID){
        return attendees.contains(userID);
    }
    public boolean removeAttendee(UUID userID){
        if (attendees.contains(userID)){
            attendees.remove(userID);
            return true;
        }
        return false;
    }


    public boolean hasSpace(){
        return this.currentNum() < this.capacity;
    }


    public boolean assignedRoom(){return hasRoom;}

    public void setRoom(UUID room) {
        this.room = room;
    }

    public void removeRoom(){
        room = null;
        hasRoom = false;
    }

    @Override
    public String toString(){
        String full;
        if (currentNum() < capacity){
            full = "available";
        }

        full = "full";
        return name+"@"+ date.toString() +", status: "+full;
    }
}
