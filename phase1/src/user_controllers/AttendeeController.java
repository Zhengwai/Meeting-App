package user_controllers;

import ScheduleSystem.*;
import message_system.AttendeeMessageController;
import users.User;
import ScheduleSystem.EventPresenter;
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
    private UserManager um;
    private Scanner scanner = new Scanner(System.in);
    private EventPresenter ep = new EventPresenter();
    private EventManager em;
    private String[] validYN = new String[]{"Y", "N"};
    private AttendeeEventController ec;
    private InputValidityChecker ivc = new InputValidityChecker();

    /**
     * Initializes AttendeeController with specific user, the UserManager and the EventManager.
     * @param thisUser the associated user.
     * @param um the UserManager
     * @param em the EventManager
     */
    public AttendeeController(User thisUser, UserManager um, EventManager em) {
        user = thisUser;
        this.um = um;
        this.em = em;
        ec = new AttendeeEventController(this.user, this.um, this.em);
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

            String input = ivc.isValidInput(ivc.validList(valid), scanner.nextLine());

            if (input.equals("1")){
                boolean r = true;
                ec.attendeeRun();
                um = new UserManager();
                em = new EventManager();
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

    private boolean message() {
        AttendeeMessageController amc = new AttendeeMessageController(this.user, this.um, this.em);
        amc.run();
        System.out.println("Would you like to enter the message system again? Enter Y for yes, N for no.");
        String confirm = ivc.isValidInput(ivc.validList(validYN), scanner.nextLine());
        return confirm.equals("Y");
    }
}