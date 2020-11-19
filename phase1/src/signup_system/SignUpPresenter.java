package signup_system;
import ScheduleSystem.Event;
import java.util.ArrayList;

public class SignUpPresenter {
    /**
     * Prints text to user interface. Alerts user that the event they selected is full and the sign up was
     * unsuccessful.
     */
    public void eventFull(){
        System.out.println("This event is full. Sign up was unsuccessful.");
    }

    /**
     * Prints text to user interface. Alerts user that they are successfully signed up to the selected event.
     *
     * @param inputEvent the name of the event
     */
    public void signUpSuccess(String inputEvent){
        System.out.println("Sign up was successful. You are now registered to see " + inputEvent);
    }

    /**
     * Prints text to user interface. Alerts user that the have successfully unregistered from the selected event.
     */
    public void cancelSuccess(){
        System.out.println("The event has been successfully cancelled");
    }

    /**
     * Prints text to the user interface. Alerts user that the event that they entered could not be found.
     */
    public void cantFindEvent(){
        System.out.println("Sorry, the event you entered could not be found.");
    }

    /**
     * Prints text to the user interface. Prompts user to enter the event they would like to sign up for.
     */
    public void promptEvent(){
        System.out.println("Please enter the event you would like to sign up for: ");
    }

    /**
     * Prints text to the user interface. Prompts user to enter the event they would like to unregister from.
     */
    public void promptCancelEvent(){
        System.out.println("Please enter the event you would like to cancel: ");
    }

    /**
     * Prints text to the user interface. Alerts user that they've already signed up for the selected event.
     */
    public void alreadySignedUp(){
        System.out.println("You've already signed up for this event");
    }

    /**
     * Prints text to the user interface. Alerts user that there is a time conflict between the selected
     * event and an event they have already signed up for.
     */
    public void eventConflict(){System.out.println("You are already registered to see an event " +
            "at the same time as this event.");}

    /**
     * Prints list of events to the user interface.
     *
     * @param allEvents a list of the events that should be displayed.
     */
    public void showEvents(ArrayList<Event> allEvents){
        for(Event event : allEvents){
            System.out.println(event);
        }
    }

    /**
     * Alerts the user that their input was not valid. Prompts them to try again.
     */
    public void notValidInput(){
        System.out.println("Not a valid input. Please try again.");
    }

    /**
     * Prompts user to enter whether they would like to sign up for another event or not.
     */
    public void signUpAgainPrompt(){
        System.out.println("Would you like to sign up for another event? \nEnter Y for yes, N for no");
    }

    /**
     * Prompts user to enter whether they would like to unregister from another event or not.
     */
    public void cancelAgainPrompt(){
        System.out.println("Would you like to cancel another event? \nEnter Y for yes, N for no");
    }
}
