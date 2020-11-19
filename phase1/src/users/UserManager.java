package users;

import ScheduleSystem.Event;
import ScheduleSystem.EventManager;

import java.io.*;
import java.util.ArrayList;
import java.util.UUID;


public class UserManager implements Serializable {
    private ArrayList<User> allUsers;
    private EventManager em;
    public User NotFoundUser = new User("NotFound", "NotFound");

    public UserManager() {
        allUsers = deserializeUsers("phase1/src/users/Users");

    }


    public boolean addUser(User newUser) {
        for (User u:allUsers){
            if (u.getUsername().equals(newUser.getUsername())){
                return false;
            }
        }

        allUsers.add(newUser);
        serializeUsers();
        return true;


    }

    public boolean addFriends(User user1, User user2){
        if (!user1.isFriendWithID(user2.getID())){
            if(!user2.isFriendWithID(user1.getID())){
                user1.addFriend(user2.getID());
                user2.addFriend(user1.getID());
                serializeUsers();
                return true;
            }
        }
        return false;
    }

    public boolean deleteFriends(User user1, User user2){
        if (user1.isFriendWithID(user2.getID())){
            if(user2.isFriendWithID(user1.getID())){
                user1.deleteFriend(user2.getID());
                user2.deleteFriend(user1.getID());
                serializeUsers();
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


    private void serializeUsers(){
        try {
            FileOutputStream fos = new FileOutputStream("/phase1/userManager.ser");
            ObjectOutput oos = new ObjectOutputStream(fos);
            oos.writeObject(allUsers);
            oos.close();
            fos.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    private ArrayList<User> temp;
    @SuppressWarnings("unchecked")
    private ArrayList<User> deserializeUsers(String path){
        try{
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            temp = (ArrayList<User>) objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }

        return temp;
    }

}
