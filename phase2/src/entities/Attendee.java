package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 * An attendee in the conference program.
 */
public class Attendee extends User implements Serializable {
    private UUID id = UUID.randomUUID();
    private String username;
    private String email = "";
    private String password;
    private ArrayList<UUID> EnrolledEventIDs;
    private ArrayList<UUID> enrolledEvents = new ArrayList<>();

    /**
     * Initializing an attendee.
     * @param username username of this attendee.
     * @param password password of this attendee.
     */
    public Attendee(String username, String password) {
        super(username, password);
        this.EnrolledEventIDs = new ArrayList<UUID>();
    }

    /**
     * Returns the type of this user.
     * The type is represented by <code>String</code>.
     * @return Returns "a" representing this type of user as attendee.
     */
    @Override
    public String getType(){
        return "a";
    }

}
