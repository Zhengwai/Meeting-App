package user_controllers;

import ScheduleSystem.*;
import message_system.OrganizerMessageController;
import ScheduleSystem.EventPresenter;
import users.User;
import users.UserGateway;
import users.UserManager;

import java.io.IOException;
import java.util.*;
/**
 * Handles organizer users' inputs and functionalities.
 */
public class OrganizerController {
    private User user;
    private Scanner scanner = new Scanner(System.in);
    private  EventPresenter ep = new EventPresenter();
    private EventManager em;
    private UserManager um;
    private OrganizerEventController oec;
    private String[] validYN = new String[]{"Y", "N"};
    private InputValidityChecker ivc = new InputValidityChecker();
    private UserGateway ug = new UserGateway();

    public OrganizerController(User user, UserManager um, EventManager em) throws ClassNotFoundException {
        this.user = user;
        this.um = um;
        this.em = em;
        oec = new OrganizerEventController(this.user, this.um, this.em);
    }

    public boolean run() throws Exception {
        boolean running = true;
        String[] validInputs = new String[]{"1", "2", "3", "4"};
        while (running){
            System.out.println("Please enter the number of corresponding choice:\n" +
                    "1.Manage Events  \n" +
                    "2.Message \n" +
                    "3.Create Speaker Account \n" +
                    "4.Exit program");
            String input = ivc.isValidInput(ivc.validList(validInputs), scanner.nextLine());

            if (input.equals("1")){
                oec.organizerRun();
            } else if (input.equals("2")){
                message();
            } else if (input.equals("3")){
                boolean added = false;
                while(!added){
                    added = createSpeakerAccount();
                    if(!added){
                        String[] validB = new String[]{"1", "0"};
                        System.out.println("Sorry, the username you've entered has already been taken." +
                                "Would you like to retry? Enter 1 for yes, 0 for no.");
                        String retry = ivc.isValidInput(ivc.validList(validB), scanner.nextLine());
                        if(retry.equals("0")){
                            added = true;
                        }
                    }
                };
            } else {
                running = false;
            }

        }
        return true;
    }

    public boolean createSpeakerAccount(){
        System.out.println("Please choose a username: ");
        String user = scanner.nextLine();
        System.out.println("Please choose a password: ");
        String pass = scanner.nextLine();
        boolean added = um.createSpeaker(user, pass);

        ug.serializeUserManager("um.ser", um);
        return added;
    }

    public boolean message() throws IOException, ClassNotFoundException {
        OrganizerMessageController omc = new OrganizerMessageController(this.user, this.um, this.em);
        omc.run();
        System.out.println("Would you like to enter the message system again? Enter Y for yes, N for no.");
        String confirm = ivc.isValidInput(ivc.validList(validYN), scanner.nextLine());
        return confirm.equals("Y");
    }

}


