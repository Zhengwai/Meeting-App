package user_controllers;

import ScheduleSystem.*;
import signup_system.SignUpPresenter;
import users.Speaker;
import users.User;
import users.UserManager;

import java.io.IOException;
import java.util.*;
/**
 * Handles organizer users' inputs and functionalities.
 */
public class OrganizerController {
    private Scanner scanner = new Scanner(System.in);
    private User user;
    private SignUpPresenter sup = new SignUpPresenter();
    private EventManager em = new EventManager();
    private UserManager um = new UserManager();
    private OrganizerEventController oec;

    public OrganizerController(User user) throws ClassNotFoundException {
        this.user = user;
        oec = new OrganizerEventController(user);
    }

    public boolean run() throws Exception {
        boolean running = true;
        String[] validInputs = new String[]{"1", "2", "3"};
        while (running){
            System.out.println("Please enter the number of corresponding choice:\n" +
                    "1.Manage Events  \n" +
                    "2.Message \n" +
                    "3.Exit program");
            String input = isValidInput(validList(validInputs), scanner.nextLine());

            if (input.equals("1")){
                boolean r = true;
                while (r){
                    r = oec.organizerRun();
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

    public boolean message(){
        return true;
    }
    }


