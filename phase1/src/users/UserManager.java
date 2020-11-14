package users;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.UUID;

public class UserManager {
    private ArrayList<User> allUsers;
    public User currentUser;

    public UserManager() {

        allUsers = new ArrayList<>();

        try{
            FileOutputStream fileOutputStream = new FileOutputStream("phase1/UserManager.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(allUsers);
            objectOutputStream.close();
            fileOutputStream.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }


}
