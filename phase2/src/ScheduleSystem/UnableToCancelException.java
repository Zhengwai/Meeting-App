package ScheduleSystem;

/**
 * An UnableToCancelException exception class.
 */
public class UnableToCancelException extends Exception{
    /**
     * Initiates the UnableToCancelException.
     */
    public UnableToCancelException(){
        super("Event was not signed up, cannot be cancelled.");
    }
}
