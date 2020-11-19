package user_controllers;

import signup_system.SignUpManager;
import signup_system.SignUpPresenter;
import users.User;
import users.UserManager;
/**
 * Handles speaker users' inputs and functionalities.
 */
public class SpeakerController {

    private User user;
    private SignUpPresenter sup = new SignUpPresenter();
    private SignUpManager sum = new SignUpManager();
    private UserManager um = new UserManager();

    public SpeakerController(User user) throws ClassNotFoundException {
        this.user = user;
    }

    public void run(){

    }

    public void viewSchedulesTalks(){

    }

    public void messageAttendees(){

    }

    public boolean message() throws IOException, ClassNotFoundException {
        UserGateway ug = new UserGateway();
        UserManager um = new UserManager();
        ArrayList<User> users;
        try {
            users = ug.deserializeUsers("/phase1/userManager.ser");
        } catch (Exception e) {
            System.out.println("Something went wrong.");
            users = new ArrayList<>();
        }

        for (User u: users){
            um.addUser(u);
        }
        SpeakerMessageController amc = new SpeakerMessageController(this.user, um, em);
        amc.run();
        System.out.println("Would you like to enter the message system again? Enter Y for yes, N for no.");
        String confirm = isValidInput(validList(validYN), scanner.nextLine());
        return confirm.equals("Y");
    }
}
