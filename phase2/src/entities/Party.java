package entities;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class Party extends Event {

    private final boolean isVIP;

    public Party(String name, int capacity, LocalDateTime startTime, LocalDateTime endTime, boolean isVIP){
        super(name, capacity, startTime, endTime);
        this.isVIP = isVIP;
    }
}
