package entities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class TED extends Event{
    private ArrayList<UUID> speakers = new ArrayList<>();

    public TED(String name, int capacity, LocalDateTime startTime, LocalDateTime endTime, boolean isVIP){
        super(name, capacity, startTime, endTime, isVIP);
        type = new SimpleStringProperty("TED");
    }

    public void addSpeaker(UUID speakerID){
        speakers.add(speakerID);
    }

    public ArrayList<UUID> getSpeakers(){
        return speakers;
    }

    @Override
    public boolean hasSpace(){
        if (speakers.size() + attendees.size() < capacity){
            return true;
        }
        return false;
    }
    @Override
    public int getTotalPeople(){
        return speakers.size() + attendees.size();
    }

    public StringProperty getType(){
        return this.type;
    }


}
