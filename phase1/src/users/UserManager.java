package users;

import java.util.ArrayList;
import java.util.UUID;

public class UserManager {
    private ArrayList<User> allUsers;
    public User currentUser;

    public UserManager() {
        allUsers = new ArrayList<>();
    }

}
