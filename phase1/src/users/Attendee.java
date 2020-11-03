package users;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Attendee extends User{
    private String type = "attendee";
    private ArrayList<UUID> EnrolledEventIDs;
    public Attendee(String username, String password) {
        super(username, password);
    }
}
