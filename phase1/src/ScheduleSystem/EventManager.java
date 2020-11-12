package ScheduleSystem;

import users.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.HashMap;

public class EventManager {
    private ArrayList<Event> events;

    public EventManager(){
        this.events = new ArrayList<>();
    }

    public boolean addEvent(Event event){
        if (this.events.contains(event)){
            return false;
        }
        else{
            this.events.add(event);
            return true;
        }
    }

    public ArrayList<Event> getEvents(){
        return this.events;
    }
    public boolean signUpUser(User user, Event event){
        for (Event i: this.events){
            if (i.getAttendees().contains(user)){
                return false;
            }
        }
        event.addAttendee(user);
        return true;
        }

    public boolean removeUser(User user, Event event){
        if (event.getAttendees().contains(user)){
            event.getAttendees().remove(user);
            return true;
        }
        else{
            return false;
        }
    }



}
