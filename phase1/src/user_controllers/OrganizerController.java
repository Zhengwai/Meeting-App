package user_controllers;

import signup_system.SignUpManager;
import signup_system.SignUpPresenter;
import users.User;

public class OrganizerController {

    private User user;
    private SignUpPresenter sup = new SignUpPresenter();
    private SignUpManager sum = new SignUpManager();

    public OrganizerController(User user){
        this.user = user;
    }
    public void planEvent(){}

    public void enterRoom(){

    }

    public void createSpeakerAccount(){

    }

}
