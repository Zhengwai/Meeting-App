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

    public SpeakerController(User user, UserManager um, EventManager em) {
        this.user = user;
        this.um = um;
        this.em = em;
    }

    public boolean run() throws IOException, ClassNotFoundException {
        boolean running = true;
        String[] validInputs = new String[]{"1", "2", "3"};
        while (running){
            System.out.println("Please enter the number of corresponding choice:\n" +
                    "1.View Assigned Events  \n" +
                    "2.Message \n" +
                    "3.Exit program");
            String input = ivc.isValidInput(ivc.validList(validInputs), scanner.nextLine());

            if (input.equals("1")){
                boolean r = true;
                while (r){
                    r = viewSchedulesTalks();
                }

            } else if (input.equals("2")){
                boolean r = true;
                while (r){
                    message();
                }
            } else {
                running = false;
            }

        }
        return true;
    }

    public boolean viewSchedulesTalks(){
        System.out.println("Here are your assigned events:");
        ArrayList<Event> events = em.getEventsByUser(user);
        for (Event e:events){
            System.out.println(e);
        }

        return viewAgain();
    }

    protected boolean viewAgain() {
        System.out.println("Would you like to see the list again? Enter 1 to view again, enter 2 to go back.");
        String[] validInputs = new String[]{"1", "2"};
        String input = ivc.isValidInput(ivc.validList(validInputs), scanner.nextLine());
        return input.equals("1");
    }

    public boolean message() throws IOException, ClassNotFoundException {
        SpeakerMessageController amc = new SpeakerMessageController(this.user, this.um, this.em);
        amc.run();
        System.out.println("Would you like to enter the message system again? Enter Y for yes, N for no.");
        String confirm = scanner.nextLine();
        return confirm.equals("Y");
    }
}