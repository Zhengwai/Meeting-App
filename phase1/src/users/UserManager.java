package users;

import java.util.ArrayList;


public class UserManager {
    private ArrayList<User> allUsers;

    public UserManager(User currentUser) {
        allUsers = new ArrayList<>();

    }

    public void addFriend(User user1, User user2){
        user1.addFriend(user2.getID());
        user2.addFriend(user1.getID());
    }


}
