package signup_system;

import users.User;
import ScheduleSystem.Event;
import ScheduleSystem.EventManager;
import java.util.ArrayList;
import java.util.Arrays;


public class SignUpManager {
    private EventManager em = new EventManager();

    public boolean checkEventFull(Event event){
        if(event.currentNum() == event.getCapacity()){
            return true;
        }
        return false;
    }

    public int signUserUp(User attendee, String inputEvent){
        Event[] results = checkEventExists(inputEvent);
        if(results.length == 0)
        {
            return 0;
        }

        if(checkEventFull(results[0])){
            return 1;
        }

        if(em.signUpUser(attendee, results[0])){
            return 2;
        }

        return 3;
    }

    public Event[] checkEventExists(String event) {
        ArrayList<Event> allEvents = em.getEvents();
        for (Event thisEvent : allEvents) {
            if (thisEvent.getName().toUpperCase().equals(event)) {
                return new Event[]{thisEvent};
            }
        }
        return new Event[]{};
    }
}
