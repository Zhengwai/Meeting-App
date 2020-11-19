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
}
