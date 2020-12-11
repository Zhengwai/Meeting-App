package entities;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class Party extends Event {

    public Party(String name, int capacity, LocalDateTime startTime, LocalDateTime endTime, boolean isVIP){
        super(name, capacity, startTime, endTime, isVIP);
    }
}
