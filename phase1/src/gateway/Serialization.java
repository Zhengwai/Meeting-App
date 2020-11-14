package gateway;



import java.io.*;
import java.util.ArrayList;
import users.*;

public class Serialization {
    public void serialize(ArrayList<User> arrayList){
        try{
            FileOutputStream fileOutputStream = new FileOutputStream("/phase1/userManager.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(arrayList);

            objectOutputStream.close();
            fileOutputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private ArrayList<User> temp;

    @SuppressWarnings("unchecked")
    public ArrayList<User> deserialization(String path){
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
