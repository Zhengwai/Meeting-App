package ScheduleSystem;

import java.util.ArrayList;

public class EventPresenter {

    /**
     * Prints text to UI. Alerts user that they are successfully signed up for their chosen event.
     * @param inputEvent the name of the event chosen by the user.
     */
    public void signUpSuccess(String inputEvent){
        System.out.println("Sign up was successful. You are now registered to see " + inputEvent + ".");
    }

    /**
     * Prints text to UI. Alerts user that they successfully unregistered from their chosen event.
     * @param inputEvent the name of the event the user unregistered from.
     */
    public void cancelSuccess(String inputEvent){
        System.out.println("The event has been successfully cancelled. You are now unregistered from "
                + inputEvent + ".");
    }

    /**
     * Prints text to UI. Prompts user to enter the event they'd like to sign up for.
     */
    public void promptEvent(){
        System.out.println("Please enter the name of the event you'd like to sign up for: ");
    }

    /**
     * Prints text to UI. Prompts user to enter the event they'd like to cancel.
     */
    public void promptCancelEvent(){
        System.out.println("Please enter the event you would like to cancel: ");
    }

    /**
     * Prints text to UI. Alerts user that they've already signed up for the event they selected.
     */
    public void alreadySignedUp(){
        System.out.println("You've already signed up for this event");
    }

    /**
     * Prints text to UI. Displays a list of events.
     * @param allEvents ArrayList of all the events that should be printed to the UI.
     */
    public void showEvents(ArrayList<Event> allEvents){
        for(Event event : allEvents){
            System.out.println(event);
        }
    }

    /**
     * Prints text to UI. Displays list of rooms.
     * @param allRooms ArrayList of all rooms that should be printed to the UI.
     */
    public void showRooms(ArrayList<Room> allRooms){
        for(Room room : allRooms){
            System.out.println(room);
        }
    }

    /**
     * Prints text to UI. Alerts user that their input is not valid.
     */
    public void notValidInput(){
        System.out.println("Not a valid input. Please try again");
    }

    /**
     * Prints text to UI. Asks user whether they would like to continue signing up for events or not.
     */
    public void signUpAgainPrompt(){
        System.out.println("Would you like to sign up for another event? \nEnter 1 for yes, 2 for no");
    }

    /**
     * Prints text to UI. Asks user whether the would like to unregister from another event or not.
     */
    public void cancelAgainPrompt(){
        System.out.println("Would you like to cancel another event? \nEnter 1 for yes, 2 for no");
    }

    /**
     * Prints text to UI. Alerts user that the event has already occurred.
     */
    public void eventHappenedPrompt(){
        System.out.println("Sorry, this event already happened");
    }

    /**
     * Prints text to UI. First message when the user selects the sign up option.
     */
    public void introduceEventsSignUp(){
        System.out.println("Here are all the events available to you:");
    }

    /**
     * Prints text to UI. First message when the user selects the cancel up option.
     */
    public void introduceEventsCancel(){
        System.out.println("Here are all the events you've signed up for:");
    }

    /**
     * Prints text to UI. Alerts user that there are no available events.
     */
    public void noAvailableEvents(){
        System.out.println("Sorry, there are no events available to you at the moment.");
    }

    /**
     * Prints text to UI. Alerts user that the selected event has a time conflict with an event they've already signed
     * up for.
     */
    public void timeConflict(){
        System.out.println("Sorry, you have already signed up for another event that takes place at the same time.");
    }

    /**
     * Prints text to UI. Prompts the user to decide whether they would like to sign up for an event or go back to the
     * event menu.
     */
    public void signUpOrGoBackPrompt(){
        System.out.println("If you'd like you sign up for one of those events, type 1, otherwise, type 2 to go back to event menu");
    }

    /**
     * Prints text to UI. Alerts the user that they are not signed up for any events.
     */
    public void noEventsToCancel(){
        System.out.println("You aren't signed up for any events.");
    }

    /**
     * Prints text to UI. Prompts user to decide whether they would like to cancel an event or go back to the
     * event menu.
     */
    public void cancelOrGoBackPrompt(){
        System.out.println("If you'd like to cancel one of the events, type 1, otherwise, type 2 to go back to event menu");
    }

    /**
     * Prints text to UI. Alerts the user that they are not signed up for the event they wish to unregister from.
     */
    public void unableToCancelPrompt(){
        System.out.println("You cannot cancel an event for which you haven't signed up for");
    }

    /**
     * Prints text to UI. Alerts user that there are no rooms available.
     */
    public void noAvailableRoomPrompt(){
        System.out.println("Sorry, there are no rooms available to you at the moment.");
    }

    public void promptEventAssign(){
        System.out.println("Please enter the event you selected: ");
    }

    public void alreadyAssigned(){
        System.out.println("This speaker has already been assigned to this event.");
    }

    public void speakerTimeConflict(){
        System.out.println("Sorry, this speaker is already registered to speak at this time.");
    }
}
