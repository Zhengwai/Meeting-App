package users;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class UserGateway {
    @SuppressWarnings("unchecked")
    public ArrayList<User> deserializeUsers(String filePath) throws ClassNotFoundException {
        try {
            InputStream file = new FileInputStream(filePath);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            ArrayList<User> users = (ArrayList<User>) input.readObject();
            input.close();
            return users;
        } catch (IOException ex) {
            //TODO: Needs logger
            return new ArrayList<>();
        }
    }

    /**
     * Serializes the UserManager's users into a .ser file.
     * @param users The users to be serialized
     * @throws IOException If something goes wrong during serialization
     */
    public void serializeUsers(String filePath, ArrayList<User> users) throws IOException {
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(users);

            objectOutputStream.close();
            fileOutputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}

