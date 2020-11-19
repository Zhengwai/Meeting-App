package ScheduleSystem;

import java.util.ArrayList;

public class EventPresenter {

    public void eventFull(){
        System.out.println("This event is full. Sign up was unsuccessful.");
    }

    public void signUpSuccess(){
        System.out.println("Sign up was successful. You are now registered to see ");
    }

    public void cancelSuccess(){
        System.out.println("The event has been successfully cancelled");
    }

    public void cantFindEvent(){
        System.out.println("Sorry, the event you entered could not be found.");
    }

    public void promptEvent(){
        System.out.println("Please enter the name of the event of your choice: ");
    }

    public void promptCancelEvent(){
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
    public void showRooms(ArrayList<Room> allRooms){
        for(Room room : allRooms){
            System.out.println(room);
        }
    }

    public void notValidInput(){
        System.out.println("Not a valid input. Please try again");
    }

    public void signUpAgainPrompt(){
        System.out.println("Would you like to sign up for another event? \nEnter 1 for yes, 2 for no");
    }

    public void cancelAgainPrompt(){
        System.out.println("Would you like to cancel another event? \nEnter 1 for yes, 2 for no");
    }

    public void eventHappenedPrompt(){
        System.out.println("Sorry, this event already happened");
    }

    public void introduceEventsSignUp(){
        System.out.println("Here are all the events available to you:");
    }

    public void introduceEventsCancel(){
        System.out.println("Here are all the events you've signed up for:");
    }

    public void noAvailableEvents(){
        System.out.println("Sorry, there are no events available to you at the moment.");
    }

    public void timeConflict(){
        System.out.println("Sorry, you have already signed up for another event that takes place at the same time.");
    }

    public void signUpOrGoBackPrompt(){
        System.out.println("If you'd like you sign up for one of those events, type 1, otherwise, type 2 to go back to event menu");
    }

    public void noEventsToCancel(){
        System.out.println("You don't have any event signed up.");
    }

    public void cancelOrGoBackPrompt(){
        System.out.println("If you'd like to cancel one of the events, type 1, otherwise, type 2 to go back to event menu");
    }

    public void unableToCancelPrompt(){
        System.out.println("You cannot cancel an event for which you haven't signed up for");
    }

    public void noAvailableRoomPrompt(){
        System.out.println("Sorry, there are no rooms available to you at the moment.");
    }


}
