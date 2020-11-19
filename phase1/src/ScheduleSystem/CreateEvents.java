package ScheduleSystem;

import ScheduleSystem.Event;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
public class CreateEvents {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //Events
        EventGateway eg = new EventGateway();
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal.set(2020, 12, 1, 11, 0);
        cal2.set(2020, 12, 3, 14, 0);
        Date date1 = cal.getTime();
        Date date2 = cal2.getTime();
        Event e1 = new Event("TalkA", date1, 4);
        Event e2 = new Event("TalkB", date2, 6);
        ArrayList<Event> allEvents = new ArrayList<Event>();
        allEvents.add(e1);
        allEvents.add(e2);
        eg.serializeEvents("phase1/Events.ser", allEvents);
        ArrayList<Event> events = eg.deserializeEvents("phase1/Events.ser");
        for (Event e:events){
            System.out.println(e);
        }

    }
}

