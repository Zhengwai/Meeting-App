package ScheduleSystem;

/**
 * An AlreadySignedUpException exception class.
 */
public class AlreadySignedUpException extends Exception{
    public AlreadySignedUpException(){
        /**
         * Initiates the AlreadySignedUpException.
         */
        super("You've already signed up for this event.");
    }
}
