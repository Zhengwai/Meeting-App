package user_controllers;

import ScheduleSystem.Event;
import ScheduleSystem.EventManager;
import signup_system.SignUpPresenter;
import users.User;
import users.UserManager;

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

    public boolean run(){
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
                    r = message();
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

    public boolean message(){
        return true;
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
}
