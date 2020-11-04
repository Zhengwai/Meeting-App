package users;

import java.util.ArrayList;
import java.util.UUID;

public class User {
    private String firstName;
    private String lastName;
    private ArrayList<UUID> friends;
    private String email;
    private String password;

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.friends = new ArrayList<>();
    }

    public void addFriend(UUID userID) {
        this.friends.add(userID);
    }

    public UUID[] getFriends() {
        return (UUID[]) this.friends.toArray();
    }

    public boolean isFriendsWith(UUID userID) {
        return this.friends.contains(userID);
    }
}
