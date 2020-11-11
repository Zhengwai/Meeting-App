package login_system;

import users.User;
import java.util.Scanner;
import java.util.List;
import java.io.*;


public class Login {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        List<String> lst;

        //Prompt for user input
        System.out.println("Please enter your username: ");
        String usern = in.nextLine();
        System.out.println("Please enter your password: ");
        String userp = in.nextLine();
    }

    public List<String> ReadFile() {
        //Deserialize
        try {
            FileInputStream file = new FileInputStream("/users/UserManager.ser");
            ObjectInputStream in_ = new ObjectInputStream(file);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


}