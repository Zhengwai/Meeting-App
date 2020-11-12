package user_controllers;

import users.User;
import conference_entities.Event;
import signup_system.SignUpPresenter;
import java.util.Scanner;

public class AttendeeController {
    private User user;
    private SignUpPresenter sUPresenter = new SignUpPresenter();
    public AttendeeController(User thisUser){
        user = thisUser;
    }

    public void browseAllTalks(){

    }

    public void signUp(){
        sUPresenter.whichEvent();

    }

    public void browseSignedUpTalks(){

    }

    public void message(){


    }
}
