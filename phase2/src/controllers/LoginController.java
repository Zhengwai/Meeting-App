package controllers;

import entities.Attendee;
import entities.Organizer;
import entities.User;
import gateways.EventGateway;
import gateways.UserGateway;
import use_cases.EventManager;
import use_cases.UserManager;

import java.util.Scanner;

/**
 * Contains functions needed for login.
 */
public class LoginController {

    Scanner in = new Scanner(System.in);
    UserGateway ug;
    UserManager um;
    EventGateway eg;
    EventManager em;
    InputValidityChecker ivc = new InputValidityChecker();

    public LoginController(){
        this.ug = new UserGateway();
        this.eg = new EventGateway();
        deserializeUM();
        deserializeEM();
        um.addUser(new Organizer("organizer", "organizer"));
    }

    /**
     * Displays a menu of choices for the User object, user, depending on its type (Organizer, Speaker, or Attendee).
     * This method keeps running and asking for user inputs until user input indicates "exit program".
     */
    public void instantiatingMethod() throws Exception {
        boolean running = true;
        while (running) {
            System.out.println("Please enter 1 to login, 2 to create a new account, 0 to exit the program");
            String[] validA = new String[]{"1", "2", "0"};
            String indicator = ivc.isValidInput(ivc.validList(validA), in.nextLine());
            if (indicator.equals("1")) {
                User user = login();
                while (user == null){
                    user = login();
                }
                if (user.getType().equals("a")) {
                    AttendeeController ac = new AttendeeController(user, um, em);
                    ac.run();
                } else if (user.getType().equals("o")) {
                    OrganizerController oc = new OrganizerController(user, um, em);
                    oc.run();
                } else {
                    SpeakerController sc = new SpeakerController(user, um, em);
                    sc.run();
                }
                serializeUM();
                serializeEM();
                in = new Scanner(System.in);
            }
            if (indicator.equals("2")){
                boolean added = false;
                while(!added) {
                    added = createNewAttendee();
                    if(!added){
                        String[] validB = new String[]{"1", "0"};
                        System.out.println("Sorry, the username you've entered has already been taken." +
                                "Would you like to retry? Enter 1 for yes, 0 for no.");
                        String retry = ivc.isValidInput(ivc.validList(validB), in.nextLine());
                        if(retry.equals("0")){
                            added = true;
                        }
                    }
                }
            }
            if (indicator.equals("0")) {
                serializeUM();
                serializeEM();
                running = false;
            }
        }
    }

    /**
     * Allows the user to log in or re-login (in the case of incorrect confidential entered).
     * Confidential checked by being compared to the arraylist consisting of information extracted from the .ser file.
     * The method returns when a correct user is found or the input information is determined to be incorrect.
     *
     * @return returns the matching user. In the case of incorrect confidential, returns null.
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
            }
            else {
                System.out.println("Incorrect username or password, please try again");
                return null;
            }
        }

        return user;
    }

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

    private boolean createNewAttendee(){
        boolean added;

        System.out.println("Please choose a username: ");
        String username = in.nextLine();
        System.out.println("Please choose a password: ");
        String password = in.nextLine();
        User a = new Attendee(username, password);

        added = um.addUser(a);
        ug.serializeUserManager("um.ser", um);

        return added;
    }

    private void deserializeUM() {
        try {
            this.um = this.ug.deserializeUserManager("um.ser");
        } catch (ClassNotFoundException e) {
            System.out.println("Couldn't find the um.ser file. Check phase1 directory.");
        }
    }

    private void serializeUM() {
        this.ug.serializeUserManager("um.ser", this.um);
    }

    private void deserializeEM() {
        try {
            this.em = this.eg.deserializeEM("em.ser");
        } catch (ClassNotFoundException e) {
            System.out.println("Couldn't find the em.ser file. Check phase1 directory.");
        }
    }

    private void serializeEM() {
        this.eg.serializeEM("em.ser", this.em);
    }
}