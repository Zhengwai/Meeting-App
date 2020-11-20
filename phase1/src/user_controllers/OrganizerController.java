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

    public OrganizerController(User user, UserManager um, EventManager em) throws ClassNotFoundException {
        this.user = user;
        this.um = um;
        this.em = em;
        oec = new OrganizerEventController(this.user, this.um, this.em);
    }

    public boolean run() throws Exception {
        boolean running = true;
        String[] validInputs = new String[]{"1", "2", "3"};
        while (running){
            System.out.println("Please enter the number of corresponding choice:\n" +
                    "1.Manage Events  \n" +
                    "2.Message \n" +
                    "3.Exit program");
            String input = ivc.isValidInput(ivc.validList(validInputs), scanner.nextLine());

            if (input.equals("1")){
                oec.organizerRun();

            } else if (input.equals("2")){
                message();
            } else {
                running = false;
            }

        }
        return true;
    }

    public boolean message() throws IOException, ClassNotFoundException {
        OrganizerMessageController amc = new OrganizerMessageController(this.user, this.um, this.em);
        amc.run();
        System.out.println("Would you like to enter the message system again? Enter Y for yes, N for no.");
        String confirm = ivc.isValidInput(ivc.validList(validYN), scanner.nextLine());
        return confirm.equals("Y");
    }

}


