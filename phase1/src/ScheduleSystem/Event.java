package ScheduleSystem;

import users.User;

import java.lang.reflect.Array;
import java.util.Date;
import java.util.Calendar;
import java.util.ArrayList;

public class Event {
    private String name;
    private Date date;
    private int capacity;
    private ArrayList<User> attendees;
    public Event(String name, Date date, int capacity){
        this.name = name;
        this.date = date;
        this.capacity = capacity;
        this.attendees = new ArrayList<>();
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

    // TODO: 11/5/2020 should be only accessible by organizer
    public ArrayList<User> getAttendees() {
        return attendees;
    }

}
