package users;

import java.util.ArrayList;
import java.util.UUID;
import gateway.Serialization;

public class UserManager {
    private ArrayList<User> allUsers;
    Serialization s = new Serialization();

    public UserManager(User currentUser) {
        allUsers = new ArrayList<>();

        s.serialize(allUsers);
    }

    public void addFriend(User user1, User user2){
        user1.addFriend(user2.getID());
        user2.addFriend(user1.getID());
    }


}
