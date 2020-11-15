package users;

import com.sun.xml.internal.fastinfoset.algorithm.UUIDEncodingAlgorithm;

import java.util.ArrayList;
import java.util.UUID;

public class User {
    private UUID id = UUID.randomUUID();
    private String username;
    private ArrayList<UUID> friends;
    private String email = "";
    private String password;
    private String type = "u";
    private ArrayList<UUID> enrolledEvents = new ArrayList<UUID>();
    private ConversationManager convMgr;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.friends = new ArrayList<UUID>();
        this.convMgr = new ConversationManager(id);
    }

    public void addFriend(UUID userID) {
        this.friends.add(userID);
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
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

    public String getUsername(){return this.username;}

    public UUID getID(){return this.id;}

    public ArrayList<UUID> getEnrolledEvents(){
        return this.enrolledEvents;
    }


}
