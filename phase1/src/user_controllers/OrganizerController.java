package user_controllers;

import ScheduleSystem.*;
import message_system.OrganizerMessageController;
import ScheduleSystem.EventPresenter;
import users.Speaker;
import users.User;
import users.UserGateway;
import users.UserManager;

import java.io.IOException;
import java.util.*;
/**
 * Handles organizer users' inputs and functionalities.
 */
public class OrganizerController {
    private Scanner scanner = new Scanner(System.in);
    private User user;
    private  EventPresenter ep = new EventPresenter();
    private EventManager em = new EventManager();
    private UserManager um = new UserManager();
    private OrganizerEventController oec;
    private String[] validYN = new String[]{"Y", "N"};

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
                    /*r = message();*/
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
            ep.notValidInput();
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
            ug.deserializeUserManager("user-manager.ser");
        } catch (Exception e) {
            System.out.println("No user-manager.ser file found. Creating a new one...");
            ug.serializeUserManager("user-manager.ser", um);
        }

        OrganizerMessageController amc = new OrganizerMessageController(this.user, um, em);
        amc.run();
        System.out.println("Would you like to enter the message system again? Enter Y for yes, N for no.");
        String confirm = isValidInput(validList(validYN), scanner.nextLine());
        return confirm.equals("Y");
    }

}


