package login_system;

import com.sun.org.apache.xpath.internal.operations.Or;
import user_controllers.AttendeeController;
import user_controllers.OrganizerController;
import user_controllers.SpeakerController;
import users.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Contains functions needed for login.
 */
public class LoginController {

    Scanner in = new Scanner(System.in);
    OrganizerController oc;
    SpeakerController sc;
    LoginGateway lg = new LoginGateway();
    UserGateway ug;
    UserManager um;
    ArrayList<User> lst;

    public LoginController() throws ClassNotFoundException {
        this.ug = new UserGateway();
        this.um = this.ug.deserializeUserManager("um.ser");
    }

    /**
     * Displays a menu of choices for the User object, user, depending on its type (Organizer, Speaker, or Attendee)
     */
    public void instantiatingMethod() throws Exception {
        boolean running = true;
        while (running) {
            System.out.println("Please enter 1 to login, 2 to create a new account, 0 to exit the program");
            String[] validA = new String[]{"1", "2", "0"};
            String indicator = isValidInput(validList(validA), in.nextLine());
            if (indicator.equals("1")) {
                User user = login();
                if (user.getType().equals("a")) {
                    AttendeeController ac = new AttendeeController(user);
                    ac.run();
                } else if (user.getType().equals("o")) {
                    OrganizerController oc = new OrganizerController(user);
                    oc.run();
                } else {
                    SpeakerController sc = new SpeakerController(user);
                    sc.run();
                }
            }
            if (indicator.equals("2")){
                boolean added = false;
                while(!added) {
                    added = createNewAttendee();
                    if(!added){
                        String[] validB = new String[]{"1", "0"};
                        System.out.println("Sorry, the username you've entered has already been taken." +
                                "Would you like to retry? Enter 1 for yes, 0 for no.");
                        String retry = isValidInput(validList(validB), in.nextLine());
                        if(retry.equals("0")){
                            added = true;
                        }
                    }
                }
            }
            else {
                running = false;
            }
        }
    }

    /**
     * Tells the user that the confidential they entered is/ are incorrect and needs to login again.
     */
    /*private User loginAgain() throws Exception {
        System.out.println("Incorrect email or password, please login again!");
        String e = promptEmail();
        String p = promptPassword();
        return login(e, p);
    }
*/
    /**
     * Prompt the user to enter their email
     *
     * @return a String storing the email they entered
     */
    public String promptEmail() {
        System.out.println("Please enter your username: ");
        return in.nextLine();
    }

    /**
     * Prompt the user to enter their password
     *
     * @return a String storing the password they entered
     */
    public String promptPassword() {
        System.out.println("Please enter your password: ");
        return in.nextLine();
    }

    /**
     * Allows the user to log in or re-login (in the case of incorrect confidential entered). Confidential checked by being compared to the arraylist consisting of information extracted from the .ser file.
     *
     *
     */
    public User login() {
        boolean running = true;
        User user = null;
        while (running) {
            System.out.println("Please enter your username: ");
            String username = in.next();
            System.out.println("Please enter your password: ");
            String password = in.next();
            if (checkUserPass(username, password)){
                running = false;
                user = getUserByCredentials(username, password);
                System.out.println("Login success!");
            } else {
                System.out.println("Incorrect username or password, please try again");
            }
        }

        return user;
    }

    /**
     * Check if the username and password entered by the user matches a signed up User object
     * @param username username entered by user
     * @param password password entered by user
     * @return boolean, true iff correct username and password, false otherwise
     */
    private boolean checkUserPass(String username, String password) {
        for (User value : um.getAllUsers()) {
            if (value.getUsername().equals(username)) {
                if (value.getPassword().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns the User object of the corresponding entered password and username
     * @param username username entered by user
     * @param password password entered by user
     * @return User object representing the corresponding user of the entered password and username
     */
    private User getUserByCredentials(String username, String password){
        for (User user : um.getAllUsers()) {
            if (user.getUsername().equals(username)) {
                if (user.getPassword().equals(password)) {
                    return user;
                }
            }
        }
        return null;
    }

    /**
     * Returns the user entered input if it is a valid input
     * @param validInputs Arraylist of strings of valid inputs
     * @param newInput new input from user
     * @return the new input from user if it is valid
     */

    private String isValidInput(ArrayList<String> validInputs, String newInput){

        while(!validInputs.contains(newInput)){
            System.out.println("Sorry, that's not a valid input. Please try again.");
            newInput = in.nextLine();
        }

        return newInput;
    }

    /**
     * Create an arraylist of the list of valid inputs and returns the arraylist
     * @param allValid list of valid inputs
     * @return arraylist of valid inputs
     */
    private ArrayList<String> validList(String[] allValid){
        ArrayList<String> validInputs = new ArrayList<String>(Arrays.asList(allValid));
        return validInputs;
    }

    /**
     * Returns true iff a new attendee is successfully created and the User Manager is updated and serialized
     * @return boolean true iff the above is achieved, false otherwise
     */
    private boolean createNewAttendee(){
        boolean added = false;

        System.out.println("Please choose a username: ");
        String username = in.nextLine();
        System.out.println("Please choose a password: ");
        String password = in.nextLine();
        User a = new Attendee(username, password);

        added = um.addUser(a);
        ug.serializeUserManager("um.ser", this.um);
        return added;
    }
}