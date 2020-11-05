package ScheduleSystem;

import users.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.HashMap;

public class EventManager {
    private HashMap<Date, Event> events;

    public EventManager(){
        this.events = new HashMap<>();
    }

    public boolean addEvent(Event event){
        if (this.events.containsKey(event.getDate())){
            return false;
        }
        else{
            this.events.put(event.getDate(), event);
            return true;
        }
    }

    public ArrayList<Event> getEvents(){
        ArrayList<Event> eventList = new ArrayList<Event>();
        for (Event i : events.values()){
           eventList.add(i);
        }
        return eventList;
    }
/*
    public boolean signUpUser(User user, Event event){
        if
    }

 */


}
