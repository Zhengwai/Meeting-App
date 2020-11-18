package signup_system;

import users.User;
import ScheduleSystem.Event;
import ScheduleSystem.EventManager;
import java.util.ArrayList;
import java.util.Arrays;


public class SignUpManager {
    private EventManager em = new EventManager();
    private SignUpPresenter sup = new SignUpPresenter();

    public boolean checkEventFull(Event event){
        if(event.currentNum() == event.getCapacity()){
            return true;
        }
        return false;
    }

    public boolean alreadySignedUp(User attendee, Event event){
        ArrayList<Event> userEvents = em.getEventsByUser(attendee);
        for(Event e: userEvents){
            if(e.getId() == event.getId()){
                return true;
            }
        }
        return false;
    }

    public void signUserUp(User attendee, String inputEvent){
        Event[] results = checkEventExists(inputEvent);
        if(results.length == 0)
        {
            sup.cantFindEvent();
        }

        if(checkEventFull(results[0])){
            sup.eventFull();
        }

        if(alreadySignedUp(attendee, results[0])){
            sup.alreadySignedUp();
        }

        if(em.signUpUser(attendee, results[0])){
            sup.signUpSuccess(inputEvent);
        }

        sup.eventConflict();
    }

    public int cancelUser(User attendee, String inputEvent){
        Event[] results = checkEventExists(inputEvent);
        if(results.length == 0)
        {
            return 0;
        }
        em.removeUser(attendee, results[0]);
        return 1;
    }

    public Event[] checkEventExists(String event) {
        ArrayList<Event> allEvents = em.getEvents();
        for (Event thisEvent : allEvents) {
            if (thisEvent.getName().toUpperCase().equals(event.toUpperCase())) {
                return new Event[]{thisEvent};
            }
        }
        return new Event[]{};
    }
}
