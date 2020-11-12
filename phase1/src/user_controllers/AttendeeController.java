package user_controllers;

import signup_system.SignUpManager;
import users.User;
import signup_system.SignUpPresenter;
import java.util.Scanner;
import ScheduleSystem.EventManager;
import ScheduleSystem.Event;
import java.util.Arrays;

public class AttendeeController {
    private User user;
    private SignUpPresenter sup = new SignUpPresenter();
    private Scanner in = new Scanner(System.in);
    private EventManager em = new EventManager();
    private SignUpManager sum = new SignUpManager();
    public AttendeeController(User thisUser){
        user = thisUser;
    }

    public void browseAllTalks(){

    }

    public void signUp(){
        String event = sup.promptEvent().toUpperCase();
        int result = sum.signUserUp(user, event);

        if(result == 0){
            sup.signUpFailure();
        }

        if(result == 1){
            sup.eventFull();
        }

        if(result == 2){
            sup.signUpSuccess(event);
        }

        if(result == 3){
            sup.alreadySignedUp();
        }
    }

    public void browseSignedUpTalks(){

    }

    public void message(){


    }
}
