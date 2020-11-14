package users;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Organizer extends User{
    private UUID id = UUID.randomUUID();
    private String username;
    private ArrayList<UUID> friends;
    private String email = "";
    private String password;
    private String type = "o";

    public Organizer(String username, String password) {
        super(username, password);
    }
}
