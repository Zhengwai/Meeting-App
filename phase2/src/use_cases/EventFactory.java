package use_cases;

import entities.Event;
import entities.Party;
import entities.Seminar;
import entities.TED;

import java.time.LocalDateTime;

public class EventFactory {
    public EventFactory() {
    }

    public Event getEvent(String eventType, String name, int capacity, LocalDateTime startTime, LocalDateTime endTime, boolean vip) {
        if (eventType == null) {
            return null;
        } else if (eventType.equalsIgnoreCase("SEMINAR")) {
            return new Seminar(name, capacity, startTime, endTime, vip);
        } else if (eventType.equalsIgnoreCase("PARTY")) {
            return new Party(name, capacity, startTime, endTime, vip);
        } else if (eventType.equalsIgnoreCase("TED")) {
            return new TED(name, capacity, startTime, endTime, vip);
        } else {
            System.out.println("A " + eventType.toLowerCase() + " is an undefined event for this program.");
            return null;
        }
    }
}
