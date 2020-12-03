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
    private final boolean isVIP;
    private StringProperty type;

    public TED(String name, int capacity, LocalDateTime startTime, LocalDateTime endTime, boolean isVIP){
        super(name, capacity, startTime, endTime);
        this.isVIP = isVIP;
        if (isVIP){
            type = new SimpleStringProperty( "vipted");
        } else {
            type = new SimpleStringProperty("ted");
        }
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
    @Override
    public int getTotalPeople(){
        return speakers.size() + attendees.size();
    }

    public StringProperty getType(){
        return this.type;
    }


}
