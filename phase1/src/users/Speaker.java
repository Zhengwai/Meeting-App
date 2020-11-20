package users;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * A speaker in the conference program.
 */
public class Speaker extends User implements Serializable {
    private UUID id = UUID.randomUUID();
    private String username;
    private String email = "";
    private String password;
    private String type;
    private ArrayList<UUID> eventIDs;
    private ArrayList<UUID> enrolledEvents = new ArrayList<UUID>();

    /**
     * Initializing a speaker.
     * @param username username of this speaker
     * @param password password of this password.
     */
    public Speaker(String username, String password) {
        super(username, password);
        this.eventIDs = new ArrayList<UUID>();
        type = "s";
    }

    /**
     * Returns the type of this user.
     * The type is represented by <code>String</code>.
     * @return Returns "s" representing this type of user as speaker.
     */
    @Override
    public String getType(){
        return "s";
    }

    /**
     * Returns list of ids of events that the speaker will hold.
     * IDs of events are in form of <code>ArrayList<UUID></code>
     * @return ArrayList of ids of the events.
     */
    public ArrayList<UUID> getSpeakerEvents(){
        return this.eventIDs;
    }
}
