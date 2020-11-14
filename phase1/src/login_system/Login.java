package login_system;

import user_controllers.AttendeeController;
import user_controllers.OrganizerController;
import user_controllers.SpeakerController;
import users.User;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

/**
 * Class that allows users to login to the system and interact with the menu of choices depending on their type.
 */
public class Login {

    private ArrayList<User> user;
    Scanner in = new Scanner(System.in);

    OrganizerController oc = new OrganizerController();
    SpeakerController sc = new SpeakerController();

    /**
     * read from a .ser file and structure the context into an arraylist
     * @param path the path or address of the file
     * @throws ClassNotFoundException if the class of the .ser file is not found
     */
    public void readFromFile(String path) throws ClassNotFoundException{
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            user = (ArrayList<User>) objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * displays a menu of choices for the User object, user, depending on its type (Organizer, Speaker, or Attendee)
     * @param user the object User, user, representing the user who just logged in
     */
    private void displayMenu(User user){
        if (user.getType().equals("Organizer")){
            System.out.println("Please enter the number of corresponding choice: 1.Enter room; 2.Create speaker account; 3. Schedule event");
            if(in.nextInt()==1){
                oc.enterRoom();
            }else if(in.nextInt()==2){
                oc.createSpeakerAccount();
            }else{
                oc.planEvent();
            }

        }else if (user.getType().equals("Speaker")){
            System.out.println("Please enter the number of corresponding choice: 1.View list of talks schedules; 2.Message attendees");
            if(in.nextInt()==1){
                sc.viewSchedulesTalks();
            }else{
                sc.messageAttendees();
            }
        }else{
            AttendeeController ac = new AttendeeController(user);
            System.out.println("Please enter the number of corresponding choice: 1.Browse all events; 2.Sign up for an event; 3.See all signed up events; 4.Message");
            if (in.nextInt()==1){
                ac.signUp();
            }else if (in.nextInt()==2){
                ac.browseAllTalks();
            }else if(in.nextInt()==3){
                ac.browseSignedUpTalks();
            }
            else{
                ac.message();
            }
        }
    }

    /**
     * Tells the user that the confidential they entered is/ are incorrect and prompt them to log in again.
     */
    private void loginAgain(){
        System.out.println("Incorrect email or password, please enter again!");
        login(promptEmail(),promptPassword());
    }

    /**
     * prompt the user to enter their email
     * @return a String storing the email they entered
     */
    private String promptEmail(){
        System.out.println("Please enter your username: ");
        return in.nextLine();
    }

    /**
     * prompt the user to enter their password
     * @return a String storing the password they entered
     */
    private String promptPassword(){
        System.out.println("Please enter your password: ");
        return in.nextLine();
    }

    /**
     * allows the user to log in or re-login (in the case of incorrect confidential entered). Confidential checked by being compared to the arraylist consisting of information extracted from the .ser file.
     * @param email the email of the user
     * @param pass the password of the user
     */
    private void login(String email, String pass){

        for (User value : user) {
            if (value.getEmail().equals(email)) {
                if (value.getPassword().equals(pass)){
                    displayMenu(value);
                }
            }
        }
        loginAgain();
    }

    public void main(String[] args) throws ClassNotFoundException {

        readFromFile("phase1/UserManager.ser");

        login(promptEmail(),promptPassword());

    }


}