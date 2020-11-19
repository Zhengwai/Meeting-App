package users;

import ScheduleSystem.Event;
import ScheduleSystem.EventManager;

import java.io.*;
import java.util.ArrayList;
import java.util.UUID;


public class UserManager implements Serializable {
    private ArrayList<User> allUsers;
    private EventManager em;
    private UserGateway ug = new UserGateway();
    public User NotFoundUser = new User("NotFound", "NotFound");

    public UserManager() {
        allUsers = new ArrayList<>();

    }


    public boolean addUser(User newUser) throws IOException {
        for (User u:allUsers){
            if (u.getUsername().equals(newUser.getUsername())){
                return false;
            }
        }

        allUsers.add(newUser);
        ug.saveToFile("/phase1/userManager.ser", this);
        return true;


    }

    public boolean addFriends(User user1, User user2) throws IOException {
        if (!user1.isFriendWithID(user2.getID())){
            if(!user2.isFriendWithID(user1.getID())){
                user1.addFriend(user2.getID());
                user2.addFriend(user1.getID());
                ug.saveToFile("/phase1/userManager.ser", this);
                return true;
            }
        }
        return false;
    }

    public boolean deleteFriends(User user1, User user2) throws IOException {
        if (user1.isFriendWithID(user2.getID())){
            if(user2.isFriendWithID(user1.getID())){
                user1.deleteFriend(user2.getID());
                user2.deleteFriend(user1.getID());
                ug.saveToFile("/phase1/userManager.ser", this);
                return true;
            }
        }
        return false;
    }

    public User getUserByName(String name) {
        for (User u:allUsers) {
            if (u.getUsername() == name) {
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




    }


