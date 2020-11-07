package conference_entities;

import java.util.ArrayList;
import java.util.UUID;

public class Event {
    private int time;
    private ArrayList<UUID> participants;
    private UUID id;
    private UUID speaker;
    private UUID room;

    public Event(int time, UUID speaker, UUID room){
        id = UUID.randomUUID();
        this.time = time;
        //add event name to the file containing events
    }


}
