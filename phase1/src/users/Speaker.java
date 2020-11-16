package users;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Speaker extends User implements Serializable {
    private UUID id = UUID.randomUUID();
    private String username;
    private String email = "";
    private String password;
    private String type = "s";
    private ArrayList<UUID> eventIDs;
    private ArrayList<UUID> enrolledEvents = new ArrayList<UUID>();
    public Speaker(String username, String password) {
        super(username, password);
        this.eventIDs = new ArrayList<UUID>();
    }
}
