package entities;

import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class Seminar extends Event {
    private UUID speaker;

    // single speaker event
    public Seminar(String name, int capacity, LocalDateTime startTime, LocalDateTime endTime, boolean vip){
        super(name,capacity,startTime,endTime, vip);
        type = new SimpleStringProperty("SEMINAR");
    }

    public void setSpeaker(UUID speaker){
        this.speaker = speaker;
    }

    public UUID getSpeaker(){
        return speaker;
    }

    @Override
    public boolean hasSpace(){
        if (1 + attendees.size() < capacity){
            return true;
        }
        return false;
    }
    @Override
    public int getTotalPeople(){
        return 1 + attendees.size();
    }
}
