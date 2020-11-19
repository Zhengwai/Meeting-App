package ScheduleSystem;

public class AlreadySignedUpException extends Exception{
    public AlreadySignedUpException(){
        super("You've already signed up for this event.");
    }
}
