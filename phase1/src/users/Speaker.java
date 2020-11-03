package users;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Speaker extends User{
    private String type = "speaker";
    private ArrayList<UUID> eventIDs;
    public Speaker(String username, String password) {
        super(username, password);
    }
}
