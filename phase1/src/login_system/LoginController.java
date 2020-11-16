package login_system;

import user_controllers.AttendeeController;
import user_controllers.OrganizerController;
import user_controllers.SpeakerController;
import users.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Contains functions needed for login.
 */
public class LoginController {

    Scanner in = new Scanner(System.in);
    OrganizerController oc;
    SpeakerController sc;
    LoginGateway lg = new LoginGateway();

    ArrayList<User> lst = lg.deserializeToArrLstOfUser("/phase1/userManager.ser");



    /**
     * Displays a menu of choices for the User object, user, depending on its type (Organizer, Speaker, or Attendee)
     * @param user the object User, user, representing the user who just logged in
     */
    private void displayMenu(User user) throws Exception {
        if (user.getType().equals("Organizer")){
            oc = new OrganizerController(user);
            System.out.println("Please enter the number of corresponding choice: 1.Enter room; 2.Create speaker account; 3. Schedule event");
            if(in.nextInt()==1){
                oc.enterRoom();
            }else if(in.nextInt()==2){
                oc.createSpeakerAccount();
            }else{
                oc.planEvent();
            }

        }else if (user.getType().equals("Speaker")){
            sc = new SpeakerController(user);
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
     * Tells the user that the confidential they entered is/ are incorrect and needs to login again.
     */
    private void loginAgain(){
        System.out.println("Incorrect email or password, please login again!");
        String e = promptEmail();
        String p = promptPassword();
        login(e,p);
    }

    /**
     * Prompt the user to enter their email
     * @return a String storing the email they entered
     */
    public String promptEmail(){
        System.out.println("Please enter your username: ");
        return in.nextLine();
    }

    /**
     * Prompt the user to enter their password
     * @return a String storing the password they entered
     */
    public String promptPassword(){
        System.out.println("Please enter your password: ");
        return in.nextLine();
    }

    /**
     * Allows the user to log in or re-login (in the case of incorrect confidential entered). Confidential checked by being compared to the arraylist consisting of information extracted from the .ser file.
     * @param email the email of the user
     * @param pass the password of the user
     */
    public void login(String email, String pass) throws Exception {

        for (User value : lst) {
            if (value.getEmail().equals(email)) {
                if (value.getPassword().equals(pass)){
                    displayMenu(value);
                }
            }
        }
        loginAgain();
    }
}