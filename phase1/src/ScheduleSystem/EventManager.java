package ScheduleSystem;

import conference_entities.Room;
import users.User;

import java.util.*;

public class EventManager {
    private ArrayList<Event> events;
    private ArrayList<Room> rooms;
    public EventManager(){
        this.events = new ArrayList<>();
        this.rooms = new ArrayList<>();
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

    public boolean addRoom(Room room){
        if (this.rooms.contains(room)){
            return false;
        }
        return true;
        }

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
class NoEventFoundException extends Exception {
}