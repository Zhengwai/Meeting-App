package users;

import ScheduleSystem.Event;
import ScheduleSystem.EventManager;
import message_system.ConversationManager;

import java.io.*;
import java.util.ArrayList;
import java.util.UUID;


public class UserManager implements Serializable {
    private ArrayList<User> allUsers;
    private EventManager em;
    public User NotFoundUser = new User("NotFound", "NotFound");

    public UserManager() {
        this.allUsers = new ArrayList<>();
        //TODO: Initialize this.em
    }


    public boolean addUser(User newUser) {
        for (User u:allUsers){
            if (u.getUsername().equals(newUser.getUsername())){
                return false;
            }
        }

        allUsers.add(newUser);
        return true;


    }

    public boolean addFriends(User user1, User user2) {
        if (!user1.isFriendWithID(user2.getID())){
            if(!user2.isFriendWithID(user1.getID())){
                user1.addFriend(user2.getID());
                user2.addFriend(user1.getID());
                return true;
            }
        }
        return false;
    }

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

    public User getUserByName(String name) {
        for (User u:allUsers) {
            if (u.getUsername().equals(name)) {
                return u;
            }
        }
        return NotFoundUser;
    }

    public User getUserByID(UUID id){
        for (User u:allUsers){
            if (u.getID().equals(id)){
                return u;
            }
        }
        return NotFoundUser;
    }

    public ArrayList<User> getAllFriends(User user){
        ArrayList<User> allFriends= new ArrayList<>();
        for (UUID id:user.getFriends()){
            allFriends.add(getUserByID(id));
        }
        return allFriends;
    }

    public ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public ArrayList<Speaker> getAllSpeakers(){
        ArrayList<Speaker> speakers = new ArrayList<>();
        for (User u: allUsers){
            if (u.getType().equals("s")){
                speakers.add((Speaker)u);
            }
        }
        return speakers;
    }

    public ArrayList<String> getAllSpeakerNames(){
        ArrayList<String> speakers = new ArrayList<>();
        for (User u: allUsers){
            if (u.getType().equals("s")){
                speakers.add(u.getUsername());
            }
        }
        return speakers;
    }

    public boolean userAvailableForEvent(User user, Event event){
        ArrayList<UUID> enrolledEvents = user.getEnrolledEvents();
        for (UUID id:enrolledEvents){
            if (em.getEventByID(id).getDate().equals(event.getDate())){
                return false;
            }
        }
        return true;
    }
    public void addEventForUser(Event event, User user) {
        user.addEvent(event.getId());
    }

    public void removeEvent(Event event, User user) {
        user.removeEvent(event.getId());
    }

    public ArrayList<User> getAllAttendees() {
        ArrayList<User> attendees = new ArrayList<>();
        for (User u:allUsers){
            if (u.getType().equals("a")){
                attendees.add(u);
            }
        }
        return attendees;

    }
}