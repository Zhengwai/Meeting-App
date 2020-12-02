package TestCreation;

import entities.Event;
import entities.TED;
import gateways.EventGateway;
import use_cases.EventManager;

import java.io.IOException;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

public class CreateEvents {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //Events
        /*EventGateway eg = new EventGateway();
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal.set(2020, 12, 1, 11, 0);
        cal2.set(2020, 12, 3, 14, 0);
        Date date1 = cal.getTime();
        Date date2 = cal2.getTime();
        Event e1 = new TED("TalkA", date1, 4);
        Event e2 = new TED("TalkB", date2, 6);
        EventManager em = new EventManager();
        em.addEvent(e1);
        em.addEvent(e2);
        eg.serializeEM("phase2/src/use_cases/EventManager.ser", em);
        EventManager em1 = eg.deserializeEM("phase2/src/use_cases/EventManager.ser");
        for (Event e:em1.getEvents()){
            System.out.println(e);
        }*/
        Set<String> allZoneIds = ZoneId.getAvailableZoneIds();
        System.out.println(allZoneIds);
    }
}
