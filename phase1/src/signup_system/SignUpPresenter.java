package signup_system;
import ScheduleSystem.Event;
import java.util.Scanner;
import java.util.ArrayList;

public class SignUpPresenter {
    private Scanner in = new Scanner(System.in);

    public void eventFull(){
        System.out.println("This event is full. Sign up was unsuccessful.");
    }

    public void signUpSuccess(String inputEvent){
        System.out.println("Sign up was successful. You are now registered to see " + inputEvent);
    }

    public void cancelSuccess(){
        System.out.println("The event has been successfully cancelled");
    }

    public void signUpFailure(){
        System.out.println("Sorry, the event you entered could not be found.");
    }

    public void cancelFailure(){
        System.out.println("Sorry, the event you entered could not be found.");
    }

    public String promptEvent(){
        System.out.println("Please enter the event you would like to sign up for: ");
        return in.nextLine();
    }

    public String promptCancelEvent(){
        System.out.println("Please enter the event you would like to cancel: ");
        return in.nextLine();
    }
    public void alreadySignedUp(){
        System.out.println("You've already signed up for this event");
    }

    public void eventDateConflict(){System.out.println("You are already registered to see an event " +
            "at the same time as this event.");}

    public void showEvents(ArrayList<String> allEvents){
        for(String event : allEvents){
            System.out.println(event);
        }
    }
}
