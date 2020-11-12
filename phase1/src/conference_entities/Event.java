package conference_entities;

import java.util.ArrayList;
import java.util.UUID;
import java.util.Date;

public class Event {
    private Date date;
    private ArrayList<UUID> participants;
    private UUID id;
    private UUID speaker;
    private UUID room;
    private String eventName;

    public Event(Date date, UUID speaker, UUID room, String name){
        eventName = name;
        id = UUID.randomUUID();
        this.date = date;
        //add event name to the file containing events
    }

    public void SignUp(){

    }

    public int getCapacity(){
        return 0;
    }

    public int getNumParticipants(){
        return participants.size();
    }

    public String getName(){
        return eventName;
    }

    public String getDate(){
        return date.toString();
    }

}
