package user_controllers;

import signup_system.SignUpManager;
import signup_system.SignUpPresenter;
import users.User;

public class SpeakerController {

    private User user;
    private SignUpPresenter sup = new SignUpPresenter();
    private SignUpManager sum = new SignUpManager();

    public SpeakerController(User user){
        this.user = user;
    }

    public void viewSchedulesTalks(){

    }

    public void messageAttendees(){

    }
}
