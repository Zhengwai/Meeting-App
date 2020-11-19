package ScheduleSystem;

public class UnableToCancelException extends Exception{
    public UnableToCancelException(){
        super("Event was not signed up, cannot be cancelled.");
    }
}
