package ScheduleSystem;

import entities.Event;
import gateways.EventGateway;
import use_cases.EventManager;

import java.io.IOException;
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
        EventManager em = new EventManager();
        em.addEvent(e1);
        em.addEvent(e2);
        eg.serializeEM("em.ser", em);
        EventManager em1 = eg.deserializeEM("em.ser");
        for (Event e:em1.getEvents()){
            System.out.println(e);
        }

    }
}
