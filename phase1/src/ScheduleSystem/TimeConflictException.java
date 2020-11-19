package ScheduleSystem;

import java.sql.Time;

public class TimeConflictException extends Exception{
    public TimeConflictException(){
        super("You have a time conflict with this event.");
    }
}
