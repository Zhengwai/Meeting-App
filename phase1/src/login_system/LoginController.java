package login_system;

import com.sun.org.apache.xpath.internal.operations.Or;
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
     */
    public void instantiatingMethod() throws Exception {
        boolean running = true;
        while (running){
            System.out.println("Please enter 1 to login, 0 to exit the program");
            int indicator = in.nextInt();
            if (indicator == 1){
                User user = login(promptEmail(), promptPassword());
                if (user.getType().equals("Attendee")){
                    AttendeeController ac = new AttendeeController(user);
                    ac.run();
                } else if(user.getType().equals("Organizer")){
                    OrganizerController oc = new OrganizerController(user);
                    oc.run();
                } else {
                    SpeakerController sc = new SpeakerController(user);
                    sc.run();
                }
            } else {
                running = false;
            }
        }
    }

    /**
     * Tells the user that the confidential they entered is/ are incorrect and needs to login again.
     */
    private User loginAgain() throws Exception {
        System.out.println("Incorrect email or password, please login again!");
        String e = promptEmail();
        String p = promptPassword();
        return login(e,p);
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
    public User login(String email, String pass) throws Exception {

        for (User value : lst) {
            if (value.getEmail().equals(email)) {
                if (value.getPassword().equals(pass)){
                    //displayMenu(value);
                    return value;
                }
            }
        }
        return loginAgain();
    }
}