package user_controllers;

import ScheduleSystem.*;
import message_system.AttendeeMessageController;
import users.User;
import ScheduleSystem.EventPresenter;
import users.UserGateway;
import users.UserManager;

import java.io.*;
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
    private EventPresenter ep = new EventPresenter();
    private EventManager em = new EventManager();
    private String[] validYN = new String[]{"Y", "N"};
    private AttendeeEventController ec;

    public AttendeeController(User thisUser) throws ClassNotFoundException {
        user = thisUser;
        ec = new AttendeeEventController(user);
    }
    /**
     * Displays a menu of choices for the Attendee object, and continuously running until user chooses to exit the program.
     */
    public void run() throws IOException, ClassNotFoundException, AlreadySignedUpException, TimeConflictException {
        String[] valid = new String[]{"1", "2", "3"};

        boolean running = true;
        while (running){
            System.out.println("Please enter the corresponding number for your choice: \n" +
                    "1. Navigating Events\n" +
                    "2. Message\n" +
                    "3. Exit program");

            String input = isValidInput(validList(valid), scanner.nextLine());

            if (input.equals("1")){
                boolean r = true;
                while (r){
                    r = ec.attendeeRun();
                    um = new UserManager();
                    em = new EventManager();
                }
            } else if (input.equals("2")){
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
            ep.notValidInput();
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
        ep.showEvents(allEvents);
    }
    /**
     * Displays all the talks this user signed up.
     */
    public void browseSignedUpTalks(){
        ArrayList<Event> allEvents = em.getEventsByUser(user);
        ep.showEvents(allEvents);
    }

    public boolean message() {
        UserGateway ug = new UserGateway();
        UserManager um = new UserManager();
        try {
            ug.deserializeUserManager("user-manager.ser");
        } catch (Exception e) {
            System.out.println("No user-manager.ser file found. Creating a new one...");
            ug.serializeUserManager("user-manager.ser", um);
        }

        AttendeeMessageController amc = new AttendeeMessageController(this.user, um);
        amc.run();
        System.out.println("Would you like to enter the message system again? Enter Y for yes, N for no.");
        String confirm = isValidInput(validList(validYN), scanner.nextLine());
        return confirm.equals("Y");
    }
}