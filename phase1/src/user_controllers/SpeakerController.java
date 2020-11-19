package user_controllers;

import ScheduleSystem.Event;
import ScheduleSystem.EventManager;
import message_system.AttendeeMessageController;
import message_system.SpeakerMessageController;
import signup_system.SignUpPresenter;
import users.Attendee;
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
    private SignUpPresenter sup = new SignUpPresenter();
    private UserManager um = new UserManager();
    private EventManager em = new EventManager();
    private Scanner scanner = new Scanner(System.in);

    public SpeakerController(User user) throws ClassNotFoundException {
        this.user = user;
    }

    public boolean run() throws IOException, ClassNotFoundException {
        boolean running = true;
        String[] validInputs = new String[]{"1", "2", "3"};
        while (running){
            System.out.println("Please enter the number of corresponding choice:\n" +
                    "1.View Assigned Events  \n" +
                    "2.Message \n" +
                    "3.Exit program");
            String input = isValidInput(validList(validInputs), scanner.nextLine());

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
        String input = isValidInput(validList(validInputs), scanner.nextLine());
        return input.equals("1");
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

    public boolean message() throws IOException, ClassNotFoundException {
        UserGateway ug = new UserGateway();
        UserManager um = new UserManager();
        try {
            um = ug.deserializeUserManager("user-manager.ser");
        } catch (Exception e) {
            System.out.println("Something went wrong.");
        }

        AttendeeMessageController amc = new AttendeeMessageController(this.user, um);
        amc.run();
        System.out.println("Would you like to enter the message system again? Enter Y for yes, N for no.");
        String confirm = scanner.nextLine();
        return confirm.equals("Y");
    }
}