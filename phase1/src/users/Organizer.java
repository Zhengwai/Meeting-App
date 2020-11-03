package users;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Organizer extends User{
    private String type = "organizer";

    public Organizer(String username, String password) {
        super(username, password);
    }
}
