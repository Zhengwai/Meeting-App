package users;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A gateway class for User that stores and reads user data.
 */
public class UserGateway {
    private String filepath;
    /**
     * deserializes the UserManager from a .ser file.
     * @param filePath path of the file for user manager.
     */
    public UserManager deserializeUserManager(String filePath) throws ClassNotFoundException {
        try {
            InputStream file = new FileInputStream(filePath);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            UserManager um= (UserManager) input.readObject();
            input.close();
            return um;
        } catch (IOException ex) {
            return new UserManager();
        }
    }

    /**
     * Serializes the UserManager into a .ser file.
     * @param filePath path of the file for user manager.
     * @param um The user manager to be serialized.
     */
    public void serializeUserManager(String filePath, UserManager um) {
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(um);

            objectOutputStream.close();
            fileOutputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

