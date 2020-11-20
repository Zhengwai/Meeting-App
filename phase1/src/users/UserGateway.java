package users;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class UserGateway {
    private String filepath;

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
     *
     * @param um The user manager to be serialized
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

