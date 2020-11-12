package signup_system;

import conference_entities.Event;

public class SignUpPresenter {

    public void eventFull(){
        System.out.println("This event is full. Sign up was unsuccessful.");
    }

    public void signUpSuccess(Event event){
        System.out.println("Sign up was successful. You are now signed up for: "
                + event.getName() + " at ");
    }

    public void whichEvent(){
        System.out.println("Please enter the event you would like to sign up for");
    }
}
