package users;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 * A user i
 * n the conference program.
 * The <code>User</code> type user only has basic functions and parameters. Most users would fall under one of its
 * child class types: <code>Attendee</code>, <code>Organizer</code>, or <code>Speaker</code>
 */
public class User implements Serializable{
    protected UUID id = UUID.randomUUID();
    protected String username;
    protected String password;
    protected ArrayList<UUID> enrolledEvents = new ArrayList<>();
    protected ArrayList<UUID> conversations = new ArrayList<>();
    protected ArrayList<UUID> friends = new ArrayList<>();

    /**
     * Initializes a user in the conference program.
     * @param username the username of the user.
     * @param password the password of the user.
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Changes the password of this user.
     * @param password the new password of this user.
     */
    public void setPassword(String password){
        this.password = password;
    }

    /**
     * Returns the type of this user.
     * The type is represented by a string. In <code>User</code> type of user, the type is defaulted as "User".
     * @return the type of this user, "User".
     */
    public String getType(){
        return "User";
    }

    /**
     * Returns the password of this user.
     * The password is represented by <code>String</code>.
     * @return the password of this user.
     */
    public String getPassword(){return this.password;}
    /**
     * Returns the username of this user.
     * The username is represented by <code>String</code>.
     * @return the username of this user.
     */
    public String getUsername(){return this.username;}
    /**
     * Returns the ID of this user.
     * The ID is randomly generated upon initialization of the user and cannot be changed.
     * The ID is unique.
     * The ID is represented by <code>UUID</code>.
     * @return the ID of this user.
     */
    public UUID getID(){return this.id;}
    /**
     * Returns the IDs of this user's enrolled events.
     * The IDs are randomly generated upon initialization of the events and cannot be changed.
     * The IDs are unique.
     * The IDs are represented by <code>ArrayList<UUID></code>.
     * @return the IDs of this user's enrolled events.
     */
    public ArrayList<UUID> getEnrolledEvents(){
        return this.enrolledEvents;
    }
    /**
     * Add the id of a conversation to this user's list of conversation IDs.
     * The IDs are randomly generated upon initialization of the conversation and cannot be changed.
     * The IDs are unique.
     * The ID is represented by <code>UUID</code>.
     * @param id the id of a conversation.
     * @return true iff the conversation has successfully been added.
     */
    public boolean addConversation(UUID id) {
        if (conversations.contains(id)){
            return false;
        }
        conversations.add(id);
        return true;
    }
    /**
     * Remove the id of a conversation from this user's list of conversation IDs.
     * The IDs are randomly generated upon initialization of the conversation and cannot be changed.
     * The IDs are unique.
     * The ID is represented by <code>UUID</code>.
     * @param id the id of a conversation.
     * @return true iff the conversation has successfully been removed.
     */
    public boolean removeConversation(UUID id){
        if (conversations.contains(id)){
            conversations.remove(id);
            return true;
        }
        return false;
    }
    /**
     * Check if this user's list of conversation IDs contains a certain ID.
     * The IDs are randomly generated upon initialization of the conversation and cannot be changed.
     * The IDs are unique.
     * The ID is represented by <code>UUID</code>.
     * @param id the id of a conversation.
     * @return true iff the passed conversation ID exists in this user's list of conversation IDs.
     */
    public boolean hasConversation(UUID id){
        return conversations.contains(id);
    }
    /**
     * Returns the IDs of this user's conversations.
     * The IDs are randomly generated upon initialization of the events and cannot be changed.
     * The IDs are unique.
     * The IDs are represented by <code>ArrayList<UUID></code>.
     * @return the IDs of this user's conversations.
     */
    public UUID[] getConversations() {
        UUID[] output = new UUID[this.conversations.size()];
        for (int i = 0; i < this.conversations.size(); i++) {
            output[i] = this.conversations.get(i);
        }
        return output;
    }

    /**
     * Add the id of a conversation to this user's list of conversation IDs.
     * The IDs are randomly generated upon initialization of the conversation and cannot be changed.
     * The IDs are unique.
     * The ID is represented by <code>UUID</code>.
     * @param id the id of an event.
     * @return true iff the conversation has successfully been added.
     */
    public boolean addEvent(UUID id){
        if (enrolledEvents.contains(id)){
            return false;
        }
        enrolledEvents.add(id);
        return true;
    }
    /**
     * Remove the id of an event from this user's list of conversation IDs.
     * The IDs are randomly generated upon initialization of the conversation and cannot be changed.
     * The IDs are unique.
     * The ID is represented by <code>UUID</code>.
     * @param id the id of an event.
     * @return true iff the event has successfully been removed.
     */
    public boolean removeEvent(UUID id){
        if (enrolledEvents.contains(id)){
            enrolledEvents.remove(id);
            return true;
        }
        return false;

    }
    /**
     * Check if this user's list of event IDs contains a certain ID.
     * The IDs are randomly generated upon initialization of the conversation and cannot be changed.
     * The IDs are unique.
     * The ID is represented by <code>UUID</code>.
     * @param id the id of an event.
     * @return true iff the passed event ID exists in this user's list of conversation IDs.
     */
    public boolean isInEvent(UUID id){
        return enrolledEvents.contains(id);
    }

    /**
     * Add another user's id to this user's list of friends' ids.
     * @param id id of a user.
     * @return true iff the friend has been successfully added.
     */
    public boolean addFriend(UUID id) {
        if (friends.contains(id)){
            return false;
        }
        friends.add(id);
        return true;
    }
    /**
     * Remove another user's id to this user's list of friends' ids.
     * @param id id of a user.
     * @return true iff the friend has been successfully removed.
     */
    public boolean deleteFriend(UUID id) {
        if (friends.contains(id)){
            friends.remove(id);
            return true;
        }
        return false;
    }

    /**
     * Returns this users friends' ids.
     * @return an array list of this user's friends' ids.
     */
    public ArrayList<UUID> getFriends() {return this.friends;}
    /**
     * Checks if this user is a friend with another user.
     * @param id the id of another user.
     * @return true iff this user's list of friends contains id.
     */
    public boolean isFriendWithID(UUID id){
        return friends.contains(id);
    }

    /**
     * Returns the string representation of this user.
     * Shows this user's type and user name.
     * @return the string representation of this user.
     */
    @Override
    public String toString(){
        return getType() + ":" + " " + username;
    }
}
