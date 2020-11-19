package users;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class User implements Serializable{
    private UUID id = UUID.randomUUID();
    private String username;
    private String email = "";
    private String password;
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

    public String getType(){
        return "User";
    }

    public String getPassword(){return this.password;}

    public String getUsername(){return this.username;}

    public UUID getID(){return this.id;}

    public ArrayList<UUID> getEnrolledEvents(){
        return this.enrolledEvents;
    }

    public boolean addConversation(UUID id) {
        if (conversations.contains(id)){
            return false;
        }
        conversations.add(id);
        return true;
    }

    public boolean removeConversation(UUID id){
        if (conversations.contains(id)){
            conversations.remove(id);
            return true;
        }
        return false;
    }

    public boolean hasConversation(UUID id){
        return conversations.contains(id);
    }

    public UUID[] getConversations() {
        UUID[] output = new UUID[this.conversations.size()];
        for (int i = 0; i < this.conversations.size(); i++) {
            output[i] = this.conversations.get(i);
        }
        return output;
    }

    public boolean isInConversation(UUID id){
        return conversations.contains(id);
    }

    public boolean addEvent(UUID id){
        if (enrolledEvents.contains(id)){
            return false;
        }
        enrolledEvents.add(id);
        return true;
    }

    public boolean removeEvent(UUID id){
        if (enrolledEvents.contains(id)){
            enrolledEvents.remove(id);
            return true;
        }
        return false;

    }

    public boolean isInEvent(UUID id){
        return enrolledEvents.contains(id);
    }


    public boolean addFriend(UUID id) {
        if (friends.contains(id)){
            return false;
        }
        friends.add(id);
        return true;
    }

    public boolean deleteFriend(UUID id) {
        if (friends.contains(id)){
            friends.remove(id);
            return true;
        }
        return false;
    }

    public ArrayList<UUID> getFriends() {return this.friends;}

    public boolean isFriendWithID(UUID id){
        return friends.contains(id);
    }
    @Override
    public String toString(){
        return getType() + ":" + " " + username;
    }
}
