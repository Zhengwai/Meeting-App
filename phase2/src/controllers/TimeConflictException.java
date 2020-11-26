package controllers;

import java.sql.Time;

/**
 * An TimeConflictException exception class.
 */
public class TimeConflictException extends Exception{
    /**
     * Initiates the TimeConflictException.
     */
    public TimeConflictException(){
        super("You have a time conflict with this event.");
    }
}
