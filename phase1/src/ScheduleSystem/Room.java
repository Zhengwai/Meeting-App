package ScheduleSystem;
import java.io.Serializable;
import java.util.UUID;
import java.util.ArrayList;

public class Room implements Serializable {
    private int capacity;
    private String roomName;
    private UUID roomId;
    private ArrayList<UUID> events;

    public Room(int capacity, String name){
        this.capacity = capacity;
        roomName = name;
        events = new ArrayList<UUID>();
        roomId = UUID.randomUUID();
    }

    public Room(){}

    public UUID getID(){
        return roomId;
    }

    public String getRoomName(){ return roomName;}

    public ArrayList<UUID> getEvents(){
        return events;
    }

    public void addEvent(UUID eventID){
        events.add(eventID);
    }

    public String toString(){
        return roomName + " capacity: " + capacity;
    }
}
