package users;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Organizer extends User implements Serializable {
    private UUID id = UUID.randomUUID();
    private String username;
    private String email = "";
    private String password;
    private String type;
    private ArrayList<UUID> enrolledEvents = new ArrayList<UUID>();

    public Organizer(String username, String password) {
        super(username, password);
        type = "o";
    }

    @Override
    public String getType(){
        return "o";
    }
}
