package users;

import com.sun.xml.internal.fastinfoset.algorithm.UUIDEncodingAlgorithm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class User implements Serializable {
    private UUID id = UUID.randomUUID();
    private String username;
    private String email = "";
    private String password;
    private String type = "User";
    private ArrayList<UUID> enrolledEvents = new ArrayList<>();
    private ArrayList<UUID> conversations = new ArrayList<>();
    private ArrayList<UUID> friends = new ArrayList<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getEmail(){return this.email;}

    public String getPassword(){return this.password;}

    public String getType(){return this.type;}

    public String getUsername(){return this.username;}

    public UUID getID(){return this.id;}

    public ArrayList<UUID> getEnrolledEvents(){
        return this.enrolledEvents;
    }

    public void addConversation(UUID id) {
        this.conversations.add(id);
    }

    public UUID[] getConversations() {
        UUID[] output = new UUID[this.conversations.size()];
        for (int i = 0; i < this.conversations.size(); i++) {
            output[i] = this.conversations.get(i);
        }
        return output;
    }

    public void addFriend(UUID id) { this.friends.add(id);}

    public void deleteFriend(UUID id) {
        friends.remove(id);
    }

    public ArrayList<UUID> getFriends() {return this.friends;}

    public boolean isFriendWithID(UUID id){
        return friends.contains(id);
    }
    @Override
    public String toString(){
        return type + ":" + " " + username;
    }
}
