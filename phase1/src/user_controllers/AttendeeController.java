package user_controllers;

import ScheduleSystem.Event;
import ScheduleSystem.EventManager;
import message_system.AttendeeMessageController;
import message_system.Message;
import message_system.MessageController;
import signup_system.SignUpManager;
import users.User;
import signup_system.SignUpPresenter;
import users.UserGateway;
import users.UserManager;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
/**
 * Handles attendee users' inputs and functionalities.
 */
public class AttendeeController {
    private User user;
    private UserManager um = new UserManager();
    private Scanner scanner = new Scanner(System.in);
    private SignUpPresenter sup = new SignUpPresenter();
    private SignUpManager sum = new SignUpManager();
    private EventManager em = new EventManager();
    private String[] validYN = new String[]{"Y", "N"};

    public AttendeeController(User thisUser){
        user = thisUser;
    }
    /**
     * Displays a menu of choices for the Attendee object, and continuously running until user chooses to exit the program.
     */
    public void run(){
        String[] valid = new String[]{"1", "2", "3", "4"};

        boolean running = true;
        while (running){
            System.out.println("Please enter the corresponding number for your choice: \n" +
                    "1. Sign up for an event\n" +
                    "2. View/cancel signed up events\n" +
                    "3. Message\n" +
                    "4. Exit program");

            String input = isValidInput(validList(valid), scanner.nextLine());

            if (input.equals("1")){
                boolean r = true;
                while (r){
                    browseAllTalks();
                    r = signUp();
                }
            } else if (input.equals("2")){
                boolean r = true;
                while (r){
                    browseSignedUpTalks();
                    r = viewCancelEvents();
                }
            } else if (input.equals("3")){
                boolean r = true;
                while (r){
                    r = message();
                }
            } else {
                running = false;
            }
        }

    }

    private String isValidInput(ArrayList<String> validInputs, String newInput){
        String checkInput = newInput.toUpperCase();

        while(!validInputs.contains(checkInput)){
            sup.notValidInput();
            checkInput = scanner.nextLine();
        }

        return checkInput;
    }

    private ArrayList<String> validList(String[] allValid){
        ArrayList<String> validInputs = new ArrayList<String>(Arrays.asList(allValid));
        return validInputs;
    }
    /**
     * Displays all the talks to user.
     */
    public void browseAllTalks(){
        ArrayList<Event> allEvents = em.getEvents();
        sup.showEvents(allEvents);
    }
    /**
     * Displays all the talks this user signed up.
     */
    public void browseSignedUpTalks(){
        ArrayList<Event> allEvents = em.getEventsByUser(user);
        sup.showEvents(allEvents);
    }
    /**
     * Lets user sign up for a talk they chose.
     * @return returns true iff the user wishes to sign up for another talk
     */
    public boolean signUp(){
        sup.promptEvent();
        String event = scanner.nextLine().toUpperCase();
        sum.signUserUp(user, event);

        sup.signUpAgainPrompt();
        String confirm = isValidInput(validList(validYN), scanner.nextLine());
        return confirm.equals("Y");
    }
    /**
     * Lets the user choose which event they wish to cancel.
     * @return returns true iff the user wishes to cancel another talk
     */
    public boolean viewCancelEvents(){
        sup.promptCancelEvent();
        String event = scanner.nextLine().toUpperCase();
        int result = sum.cancelUser(user, event);
        if (result == 0){
            sup.cantFindEvent();
        }

        sup.cancelSuccess();

        sup.cancelAgainPrompt();
        String confirm = isValidInput(validList(validYN), scanner.nextLine());
        return confirm.equals("Y");
    }
    /**
     * Prompts user to the message system.
     * @return returns true iff the user wishes to sign up for another talk
     */
    public boolean message(){
        UserGateway ug = new UserGateway();
        UserManager um = new UserManager();

        try {
            um = ug.readFromFile("/phase1/userManager.ser");
        } catch (Exception e) {
            System.out.println("Something went wrong.");
        }

        AttendeeMessageController amc = new AttendeeMessageController(this.user, um);
        amc.run();
        System.out.println("Would you like to enter the message system again? Enter Y for yes, N for no.");
        String confirm = isValidInput(validList(validYN), scanner.nextLine());
        return confirm.equals("Y");
    }
}
