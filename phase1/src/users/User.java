package users;

import java.util.ArrayList;
import java.util.UUID;

public class User {
    private String firstName;
    private String lastName;
    private ArrayList<UUID> friends;
    private String email;
    private String password;
    private String type;

    public User(String firstName, String lastName, String email, String password, String type) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.friends = new ArrayList<>();
        this.type = type;
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

    public String getEmail(){return this.email;}

    public String getPassword(){return this.password;}

    public String getType(){return this.type;}
}
