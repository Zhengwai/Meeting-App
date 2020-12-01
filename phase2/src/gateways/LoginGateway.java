package gateways;

import java.io.*;
import java.util.ArrayList;

import entities.User;

/**
 * Serialize and deserialize between arraylists of User objects and .ser files
 */
public class LoginGateway implements Serializable{
    /**
     * Serialize arraylists to .ser file
     * @param arrayList the arraylist that wants to be serialized
     * @param address the location of the .ser file to be saved at after serialization
     */
    public void writeObject(ArrayList<User> arrayList, String address){
        try{
            File file = new File(address);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(arrayList);

            objectOutputStream.close();
            fileOutputStream.close();
        }catch (IOException e){
            e.printStackTrace();
            System.err.println("write");
        }
    }

    private ArrayList<User> temp;

    /**
     * Deserialize .ser file into arraylists of User objects
     * @param path the location or address of the .ser file
     * @return returns an arraylist of User objects
     */
    @SuppressWarnings("unchecked")
    public ArrayList<User> readObject(String path){
        try{
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            temp = (ArrayList<User>) objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();

        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
            System.err.println("read");
        }

        return temp;
    }

    public boolean verifyLogin(String username, String password){
        //TODO: Implement this with database;
        return false;
    }
}
