package conference_entities;
import java.util.UUID;
import java.util.ArrayList;

public class Room {
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
}
