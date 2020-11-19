package ScheduleSystem;

import users.User;

import java.io.*;
import java.util.ArrayList;

public class Serialization {
    public void serializeArrLst(ArrayList<User> arrayList, String address){
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

    public ArrayList<Object> deserializeToArrLst(String path){
        try{
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            ArrayList<Object> temp = (ArrayList<Object>) objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();
            return temp;
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }

        return null;
    }
}

