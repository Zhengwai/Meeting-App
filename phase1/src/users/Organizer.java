package users;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
/**
 * An attendee in the conference program.
 */
public class Organizer extends User implements Serializable {
    //private UUID id = UUID.randomUUID();
    //private String username;
    //private String email = "";
    //private String password;
    private String type;
    //private ArrayList<UUID> enrolledEvents = new ArrayList<UUID>();
    /**
     * Initializing an organizer.
     * @param username username of this organizer.
     * @param password password of this organizer.
     */
    public Organizer(String username, String password) {
        super(username, password);
        type = "o";
    }
    /**
     * Returns the type of this user.
     * The type is represented by <code>String</code>.
     * @return Returns "o" representing this type of user as organizer.
     */
    @Override
    public String getType(){
        return "o";
    }
}
