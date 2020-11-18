package signup_system;
import ScheduleSystem.Event;
import java.util.Scanner;
import java.util.ArrayList;

public class SignUpPresenter {

    public void eventFull(){
        System.out.println("This event is full. Sign up was unsuccessful.");
    }

    public void signUpSuccess(String inputEvent){
        System.out.println("Sign up was successful. You are now registered to see " + inputEvent);
    }

    public void cancelSuccess(){
        System.out.println("The event has been successfully cancelled");
    }

    public void cantFindEvent(){
        System.out.println("Sorry, the event you entered could not be found.");
    }

    public void promptEvent(){
        System.out.println("Please enter the event you would like to sign up for: ");
    }

    public String promptCancelEvent(){
        System.out.println("Please enter the event you would like to cancel: ");
    }
    public void alreadySignedUp(){
        System.out.println("You've already signed up for this event");
    }

    public void eventConflict(){System.out.println("You are already registered to see an event " +
            "at the same time as this event.");}

    public void showEvents(ArrayList<Event> allEvents){
        for(Event event : allEvents){
            System.out.println(event);
        }
    }

    public void notValidInput(){
        System.out.println("Not a valid input. Please try again");
    }

    public void signUpAgainPrompt(){
        System.out.println("Would you like to sign up for another event? \nEnter Y for yes, N for no");
    }

    public void cancelAgainPrompt(){
        System.out.println("Would you like to cancel another event? \nEnter Y for yes, N for no");
    }
}
