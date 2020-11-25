package user_controllers;

import ScheduleSystem.AttendeeEventController;
import ScheduleSystem.Event;
import ScheduleSystem.EventManager;
import message_system.SpeakerMessageController;
import ScheduleSystem.EventPresenter;
import users.User;
import users.UserGateway;
import users.UserManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Handles speaker users' inputs and functionalities.
 */
public class SpeakerController {

    private User user;
    private EventPresenter ep = new EventPresenter();
    private UserManager um;
    private EventManager em;
    private Scanner scanner = new Scanner(System.in);
    private InputValidityChecker ivc = new InputValidityChecker();
    /**
     * Initializes SpeakerController with specific user, the UserManager and the EventManager.
     * @param user the associated user.
     * @param um the UserManager
     * @param em the EventManager
     */
    public SpeakerController(User user, UserManager um, EventManager em) {
        this.user = user;
        this.um = um;
        this.em = em;
    }
    /**
     * Displays a menu of choices for the Speaker object, and continuously running until user chooses to exit the program.
     */
    public boolean run() throws IOException, ClassNotFoundException {
        boolean running = true;
        String[] validInputs = new String[]{"1", "2", "3"};
        while (running){
            System.out.println("Please enter the number of corresponding choice:\n" +
                    "1.View Assigned Events  \n" +
                    "2.Message \n" +
                    "3.Log out");
            String input = ivc.isValidInput(ivc.validList(validInputs), scanner.nextLine());

            if (input.equals("1")){
                boolean r = true;
                while (r){
                    r = viewSchedulesTalks();
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
        return true;
    }

    private boolean viewSchedulesTalks(){
        System.out.println("Here are your assigned events:");
        ArrayList<Event> events = em.getEventsByUser(user);
        for (Event e:events){
            System.out.println(e);
        }

        return viewAgain();
    }

    private boolean viewAgain() {
        System.out.println("Would you like to see the list again? Enter 1 to view again, enter 2 to go back.");
        String[] validInputs = new String[]{"1", "2"};
        String input = ivc.isValidInput(ivc.validList(validInputs), scanner.nextLine());
        return input.equals("1");
    }

    private boolean message() throws IOException, ClassNotFoundException {
        String[] validYN = new String[]{"Y", "N"};
        SpeakerMessageController smc = new SpeakerMessageController(this.user, this.um, this.em);
        smc.run();
        System.out.println("Would you like to enter the message system again? Enter Y for yes, N for no.");
        String confirm = ivc.isValidInput(ivc.validList(validYN), scanner.nextLine());
        return confirm.equals("Y");
    }
}