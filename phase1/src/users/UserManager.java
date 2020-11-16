package users;

import ScheduleSystem.Event;
import ScheduleSystem.EventManager;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.UUID;


public class UserManager {
    private ArrayList<User> allUsers;
    private EventManager em;
    private User NotFoundUser = new User("NotFound", "NotFound");

    public UserManager() {
        allUsers = new ArrayList<>();

    }

    public User getUserByID(UUID userid){
        for (User u : allUsers){
            if (u.getID() == userid) {
                return u;
            }
        }
        return NotFoundUser;
    }

    public boolean addUser(User newUser) throws Exception{
        for (User u:allUsers){
            if (u.getUsername().equals(newUser.getUsername())){
                return false;
            }
        }

        allUsers.add(newUser);
        try {
            FileOutputStream fos = new FileOutputStream("/phase1/userManager.ser");
            ObjectOutput oos = new ObjectOutputStream(fos);
            oos.writeObject(allUsers);
            oos.close();
            fos.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return true;


    }


}
