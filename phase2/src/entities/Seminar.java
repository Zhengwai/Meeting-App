package entities;

import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Seminar extends Event {
    public Seminar(String name, int capacity, LocalDateTime startTime, LocalDateTime endTime){
        super(name,capacity,startTime,endTime);
        type = new SimpleStringProperty("seminar");
    }
}
