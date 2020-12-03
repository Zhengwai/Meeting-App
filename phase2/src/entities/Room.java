package entities;
import java.io.Serializable;
import java.util.UUID;
import java.util.ArrayList;

/**
 * A room in the conference program.
 */
public class Room implements Serializable {
    private int capacity;
    private String roomName;
    private UUID roomId;
    private ArrayList<UUID> events;

    /**
     * Initializes a room called <code>name</code> with a capacity of <code>capacity</code> number of people.
     * @param capacity number of people allowed in the room.
     * @param name name of the room.
     */
    public Room(int capacity, String name){
        this.capacity = capacity;
        roomName = name;
        events = new ArrayList<UUID>();
        roomId = UUID.randomUUID();
    }

    /**
     * Initializes a placeholder room.
     */
    public Room(){}

    /**
     * Returns the randomly generated UUID for this room.
     * @return the UUID of this room.
     */
    public UUID getID(){
        return roomId;
    }

    /**
     * Returns the name of this room in a String form.
     * @return the name of this room.
     */
    public String getRoomName(){ return roomName;}

    /**
     * @return the capacity of the room
     */
    public int getCapacity(){return this.capacity; }

    /**
     * Returns the list of events the room has been assigned to.
     * @return an ArrayList of events the room has been assigned to.
     */
    public ArrayList<UUID> getEvents(){
        return events;
    }

    /**
     * Assign an event to this room.
     * @param eventID the event to be assigned.
     */
    public void addEvent(UUID eventID){
        events.add(eventID);
    }

    /**
     * Returns a string representation of this room.
     * @return a string that has this room's name and capacity.
     */
    public String toString(){
        return roomName + " capacity: " + capacity;
    }

    public UUID getRoomId() {
        return roomId;
    }
}
