package entities;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class TED extends Event{
    private ArrayList<UUID> speakers = new ArrayList<>();
    private final boolean isVIP;

    public TED(String name, int capacity,ZonedDateTime startTime, ZonedDateTime endTime, boolean isVIP){
        super(name, capacity, startTime, endTime);
        this.isVIP = isVIP;
    }
    public void addSpeaker(UUID speakerID){
        speakers.add(speakerID);
    }

    @Override
    public boolean hasSpace(){
        if (speakers.size() + attendees.size() < capacity){
            return true;
        }
        return false;
    }


}
