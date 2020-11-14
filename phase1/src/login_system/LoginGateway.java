package login_system;

import java.io.*;
import java.util.ArrayList;
import users.*;

/**
 * Serialize and deserialize between arraylists of User objects and .ser files
 */
public class LoginGateway {
    /**
     * Serialize arraylists to .ser file
     * @param arrayList the arraylist that wants to be serialized
     * @param address the location of the .ser file to be saved at after serialization
     */
    public void serializeArrLstOfUser(ArrayList<User> arrayList, String address){
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(address);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(arrayList);

            objectOutputStream.close();
            fileOutputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private ArrayList<User> temp;

    /**
     * Deserialize .ser file into arraylists of User objects
     * @param path the location or address of the .ser file
     * @return returns an arraylist of User objects
     */
    @SuppressWarnings("unchecked")
    public ArrayList<User> deserializeToArrLstOfUser(String path){
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
