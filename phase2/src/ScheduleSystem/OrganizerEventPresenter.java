package ScheduleSystem;

import users.Speaker;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Date;

public class OrganizerEventPresenter {
    public OrganizerEventPresenter(){

    }

    public void yearPrompt(){
        System.out.println("What year would like the event to take place? (Enter any year after 1900)");
    }

    public void monthPrompt(){
        System.out.println("What month would like the event to take place? (Enter any month from (1-12)");
    }

    public void dayPrompt(){System.out.println("What day would like the event to take place? (Enter any valid day within the month, ranging from 1-31");

    }

    public void hourPrompt(){System.out.println("Enter the hour you want the event to take place (9 - 16)");}

    public void eventNamePrompt(){System.out.println("What would you like to call this event?");}

    public void eventConfirmPrompt(String eventName, Date date){
        System.out.println("You are about to add an event called " + eventName + " on " + date.toString() + ", enter 1 to confirm and add this event, enter 2 to start over");
    }

    public void eventSuccessfullyAddedPrompt(){
        System.out.println("Successfully added event");
    }

    public void createEventAgainPrompt(){
        System.out.println("Would you like to create another event? Enter 1 to create another one, enter 2 to go back to the event main menu.");
    }

    public void createEventOrGoBackPrompt(){
        System.out.println("If you would like to create an event, enter 1. To return to previous menu, enter 2.");
    }

    public void createRoomOrGoBackPrompt(){
        System.out.println("If you would like to create a room, enter 1. To return to previous menu, enter 2.");
    }

    public void roomCapacityPrompt(){
        System.out.println("What would be the capacity of this room? Enter any integer number > 1");
    }
    public void roomNamePrompt(){
        System.out.println("What would be the name of this room?");
    }

    public void confirmRoomPrompt(String name, int capacity){
        System.out.println("You are about to add a room called " + name + " with capacity of " + capacity + ", enter 1 to confirm and add this room, enter 2 to start over");
    }

    public void roomSuccessfullyAddedPrompt(){
        System.out.println("Successfully added room");
    }

    public void createRoomAgainPrompt(){
        System.out.println("Would you like to create another room? Enter 1 to create another one, enter 2 to go back to the event main menu.");
    }

    public void promptRoom(){
        System.out.println("Please enter the name of the room of your choice: ");
    }
    public void eventAlreadyHasRoom(String roomName){
        System.out.println("Room: " + roomName + " has already been assigned to this event, assigning another room will free the assigned room up. Enter 1 to proceed, enter 2 to go back");
    }
    public void confirmAssignRoomPrompt(String roomName, String eventName){
        System.out.println("You are about to assign room: "+roomName+" to event: "+eventName+", enter 1 to confirm, enter 2 to go back");

    }

    public void timeConflictRoomAssignment(){
        System.out.println("The room is being used for another event at the same time slot, please try again.");
    }
    public void assignRoomAgainPrompt(){
        System.out.println("Would you like to assign room again? Enter 1 to create another one, enter 2 to go back to the event main menu.");
    }

    public void assignRoomOrGoBackPrompt(){
        System.out.println("If you would like to assign room, enter 1. To return to previous menu, enter 2.");
    }
    public void roomSuccessfullyAssigned(){
        System.out.println("Room successfully assigned!");
    }
    public void noAvailableSpeakerPrompt(){
        System.out.println("Sorry, no speaker is available at the moment.");
    }
    public void assignSpeakerOrGoBackPrompt(){
        System.out.println("If you would like to assign speaker, enter 1. To return to previous menu, enter 2.");
    }
    public void timeConflictSpeakerAssignment(){
        System.out.println("The speaker is assigned another event at the same time slot, please try again.");
    }

    public void assignSpeakerAgainPrompt(){
        System.out.println("Would you like to assign speaker again? Enter 1 to create another one, enter 2 to go back to the event main menu.");
    }
    public void eventAlreadyHasSpeaker(String speakerName){
        System.out.println("Speaker: " + speakerName + " has already been assigned to this event, assigning another speaker will free the assigned speaker up. Enter 1 to proceed, enter 2 to go back");
    }
    public void showSpeakers(ArrayList<Speaker> speakers){
        for (Speaker s : speakers){
            System.out.println(s.getUsername());
        }
    }
    public void promptSpeaker(){
        System.out.println("Please enter the name of the speaker of your choice: ");
    }
    public void confirmAssignSpeakerPrompt(String username, String eventName){
        System.out.println("You are about to assign speaker: "+username+" to event: "+eventName+", enter 1 to confirm, enter 2 to go back");
    }
    public void speakerSuccessfullyAssigned(){
        System.out.println("Speaker successfully assigned!");
    }
}
