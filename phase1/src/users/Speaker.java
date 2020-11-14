package users;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Speaker extends User{
    private UUID id = UUID.randomUUID();
    private String username;
    private ArrayList<UUID> friends;
    private String email = "";
    private String password;
    private String type = "s";
    private ArrayList<UUID> eventIDs;
    public Speaker(String username, String password) {
        super(username, password);
        this.eventIDs = new ArrayList<UUID>();
    }
}
