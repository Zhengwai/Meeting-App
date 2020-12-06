package use_cases;

import Repository.UserData;
import com.sun.xml.internal.bind.v2.TODO;
import database.UserDataMapper;
import entities.*;
import gateways.UserDataGateway;
import gateways.UserGateway;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * A use case class for user that manages user related activities.
 */
public class UserManager implements Serializable, Observable {
    private ArrayList<User> allUsers = new ArrayList<>();
    public User NotFoundUser = new User("NotFound", "NotFound");
    private UserDataGateway udg = new UserDataMapper();
    //private UserData userData = new UserGateway();

    /**
     * Initializes this UserManager.
     */
    public UserManager() {
        allUsers = udg.fetchAllUsers();
    }

    /**
     * Adds a user to this system.
     * @param newUser the user to be added.
     * @return true iff the user has been successfully added.
     */

    public boolean addUser(User newUser) {
        allUsers.add(newUser);
        udg.insertNewUser(newUser);
        return true;
    }

    public boolean registerAttendee(String username, String password){
        Attendee a = new Attendee(username, password);
        allUsers.add(a);
        udg.insertNewUser(a);
        return true;
    }

    public boolean registerOrganizer(String username, String password){
        Organizer o = new Organizer(username, password);
        allUsers.add(o);
        udg.insertNewUser(o);
        return true;
    }
    public boolean registerSpeaker(String username, String password){
        Speaker s = new Speaker(username, password);
        allUsers.add(s);
        udg.insertNewUser(s);
        return true;
    }
    public boolean registerVIP(String username, String password){
        VIP vip = new VIP(username, password);
        allUsers.add(vip);
        udg.insertNewUser(vip);
        return true;
    }

    public boolean login(String username, String password){
        //TODO:
        return false;
    }

    /**
     * Adds two users as friends. Assumes valid UUIDs are passed.
     * @param userID1 ID of first user to be added.
     * @param userID2 ID of second user to be added.
     * @return true iff the two users have been befriended successfully.
     */
    public boolean addFriends(UUID userID1, UUID userID2) {
        User user1 = getUserByID(userID1);
        System.out.println(user1.getUsername());
        User user2 = getUserByID(userID2);
        System.out.println(user2.getUsername());
        if (!user1.isFriendWithID(user2.getID())){
            if(!user2.isFriendWithID(user1.getID())){
                user1.addFriend(user2.getID());
                user2.addFriend(user1.getID());
                udg.updateUserFriends(userID1, user1.getFriends());
                udg.updateUserFriends(userID2, user2.getFriends());
                return true;
            }
        }
        return false;
    }

    /**
     * Unfriends two users.
     * @param user1 first user to be unfriended.
     * @param user2 second user to be unfriended.
     * @return true iff the two users have been unfriended successfully.
     */
    public boolean deleteFriends(User user1, User user2) {
        if (user1.isFriendWithID(user2.getID())){
            if(user2.isFriendWithID(user1.getID())){
                user1.deleteFriend(user2.getID());
                user2.deleteFriend(user1.getID());
                return true;
            }
        }
        return false;
    }

    /**
     * Search the user with specific user name.
     * @param name the name in search.
     * @return the first user found with its username matching with <code>name</code>.
     */
    public User getUserByName(String name) {
        for (User u:allUsers) {
            if (u.getUsername().equals(name)) {
                return u;
            }
        }
        return NotFoundUser;
    }

    /**
     * Search the user with specific <code>UUID</code>
     * @param id the <code>UUID</code> in search.
     * @return the user that matches the <code>id</code>. If no use is found, return <code>NotFoundUser</code>.
     */
    public User getUserByID(UUID id){
        for (User u:allUsers){
            if (u.getID().equals(id)){
                return u;
            }
        }
        return NotFoundUser;
    }

    public ArrayList<UUID> getAllFriendIDs(UUID userID){
        User user = getUserByID(userID);
        try {
            return new ArrayList<>(user.getFriends());
        } catch (NullPointerException e) {
            System.out.println("This user has no friends.");
            return new ArrayList<>();
        }
    }

    /**
     * Returns all users in the system.
     * @return an ArrayList of all users in the system.
     */
    public ArrayList<User> getAllUsers() {
        return allUsers;
    }

    /**
     * Returns all speakers in the system.
     * @return an ArrayList of all speakers in the system.
     */
    public ArrayList<Speaker> getAllSpeakers(){
        ArrayList<Speaker> speakers = new ArrayList<>();
        for (User u: udg.fetchAllUsers()){
            if (u.getType().equals("s")){
                speakers.add((Speaker)u);
            }
        }
        return speakers;
    }

    /**
     * Returns all speakers in the system.
     * @return an ArrayList of all speakers in the system.
     */
    public ArrayList<User> getAllSpeakersUser(){
        ArrayList<User> speakers = new ArrayList<>();
        for (User u: allUsers){
            if (u.getType().equals("s")){
                speakers.add(u);
            }
        }
        return speakers;
    }

    /**
     * Returns all the names of the speakers in the system.
     * @return an ArrayList of all the names of speakers in the system.
     */
    public ArrayList<String> getAllSpeakerNames(){
        ArrayList<String> speakers = new ArrayList<>();
        for (User u: allUsers){
            if (u.getType().equals("s")){
                speakers.add(u.getUsername());
            }
        }
        return speakers;
    }

    /**
     * Enroll an user for an ever, add the id of the event to the user's event ID list.
     * @param event event to be added.
     * @param user user for the event to be added.
     */
    public void addEventForUser(Event event, User user) {
        user.addEvent(event.getId());
    }

    /**
     * Remove an user for an ever, remove the id of the event from the user's event ID list.
     * @param event event to be removed.
     * @param user user for the event to be removed.
     */
    public void removeEvent(Event event, User user) {
        user.removeEvent(event.getId());
    }

    /**
     * Returns all the attendees in the system.
     * @return an ArrayList of all attendees in the system.
     */
    public ArrayList<User> getAllAttendees() {
        ArrayList<User> attendees = new ArrayList<>();
        for (User u:allUsers){
            if (u.getType().equals("a")){
                attendees.add(u);
            }
        }
        return attendees;
    }

    /**
     * Add a speaker to the system.
     * @param username username of the speaker
     * @param password password of the speaker
     * @return true iff the speaker has been successfully added.
     */
    public boolean createSpeaker(String username, String password){
        return addUser(new Speaker(username, password));
    }

    /**
     * Add an organizer to the system.
     * @param username username of the organizer
     * @param password password of the organizer
     * @return true iff the speaker has been successfully added.
     */
    public boolean createOrganizer(String username, String password){
        return addUser(new Speaker(username, password));
    }

    /**
     * Returns the type of given user
     * @param userID The UUID of the user whose type you want to return
     * @return type of user with UUID userID
     */
    public String getType(UUID userID) {return getUserByID(userID).getType();}

   /* public void saveData(){
        this.userData.serializeUserManager("phase2/src/use_cases/UserManager.ser", this);
    } */

    /**
     * Populates <code>this.allUsers</code> with instances of all Users from the database.
     * Instantiates appropriate types as well.
     */
    public void getUsersFromDB() {
        allUsers = udg.fetchAllUsers();
    }


    public User verifyLogin(String username, String password){
        for (User u : allUsers){
            if (u.getUsername().equals(username)){
                if (u.getPassword().equals(password)){
                    return u;
                }
            }
        }
        return null;
    }

    public boolean isValidUserName(String username){
        for (User u:allUsers){
            if (u.getUsername().equals(username)){
                return false;
            }
        }
        return true;
    }

    public List<String> getAllFriendNames(UUID userID){
        User user = getUserByID(userID);
        List<String> friendNames = new ArrayList<String>();
        try {
            ArrayList<UUID> friendID = getAllFriendIDs(userID);
            System.out.println(friendID.size());
            for(UUID f: friendID){
                friendNames.add(getUserByID(f).getUsername());
            }
            System.out.println(friendNames.size());
            return friendNames;
        } catch (NullPointerException e) {
            System.out.println("This user has no friends.");
            return friendNames;
        }
    }

    public List<String> getAllNonFriendNames(UUID userID){
        User user = getUserByID(userID);
        List<String> nonFriendNames = new ArrayList<String>();
        try {
            ArrayList<UUID> friendID = getAllFriendIDs(userID);
            ArrayList<User> all = allUsers;
            for(User u: all){
                if(!friendID.contains(u.getID()) && !u.getID().equals(user.getID())){
                    nonFriendNames.add(u.getUsername());
                }
            }
            return nonFriendNames;
        } catch (NullPointerException e) {
            nonFriendNames.add("You're pals with everyone");
            return nonFriendNames;
        }
    }
    
    @Override
    public void addListener(InvalidationListener listener) {

    }

    @Override
    public void removeListener(InvalidationListener listener) {

    }
}