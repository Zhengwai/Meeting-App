package entities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;

/**
 * The event entity is a class responsible for the events in the convention. Each instance of an event models a
 * separate event.
 */
public class Event implements Serializable {
    protected UUID id;
    protected StringProperty name;
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;
    protected int capacity;
    protected ArrayList<UUID> attendees;
    protected UUID room;
    protected String description = "No information on this event has been disclosed yet.";
    protected DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
    protected DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;

    protected StringProperty type = new SimpleStringProperty();
    protected StringProperty status = new SimpleStringProperty();

    public StringProperty getDateString() {
        return new SimpleStringProperty(dateFormatter.format(startTime));
    }

    public StringProperty getType() {
        return type;
    }

    public StringProperty getStartTimeString() {
        return new SimpleStringProperty(timeFormatter.format(startTime));
    }

    public StringProperty getEndTimeString() {
        return new SimpleStringProperty(timeFormatter.format(endTime));
    }

    public StringProperty getCapacityString() {
        return new SimpleStringProperty(getTotalPeople() + "/" + capacity);
    }

    /**
     * @param name the name of the event
     * @param capacity the maximum attendee capacity of the event
     */
    public Event(String name, int capacity, LocalDateTime startTime, LocalDateTime endTime){
        this.name = new SimpleStringProperty(name.toUpperCase());
        this.startTime = startTime;
        this.endTime = endTime;
        this.capacity = capacity;
        this.attendees = new ArrayList<>();
        this.id = UUID.randomUUID();

    }

    /**
     * Second constructor for event without for case there is no event information.
     */
    public Event() {
        this.id = UUID.randomUUID();
        this.attendees = new ArrayList<>();
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }


    public void setAttendees(ArrayList<UUID> attendees) {
        this.attendees = attendees;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the id of the event
     */
    public UUID getId(){
        return this.id;
    }

    /**
     * @return the name of the event
     */
    public StringProperty getName(){
        return this.name;
    }

    /**
     * @return date of the event as a date object
     */


    /**
     * @return the the maximum capacity of the event
     */
    public int getCapacity(){
        return this.capacity;
    }

    /**
     * @set the capacity
     */
    public void setCapacity(int c){this.capacity = c; }

    /**
     * @return the current amount of attendees registered for the event
     */
    public int currentNum(){
        return attendees.size();
    }

    /**
     * @return an ArrayList of the attendees
     */
    public ArrayList<UUID> getAttendees() {
        return attendees;
    }



    /**
     * @return the assigned room of the event if it exists. Null otherwise.
     */
    public UUID getRoom(){
        if (this.assignedRoom()){
            return room;
        }
        return null;
    }

    /**
     * add an individual that will attend the event
     * @param userID the user id of the attendee
     * @return true if and only if the attendee was successfully added (the attendee was not already registered)
     */
    public boolean addAttendee(UUID userID){
        if (attendees.contains(userID)){
            return false;
        }
        attendees.add(userID);
        return true;
    }

    /**
     * check the event if an attendee with a specified user id is expected to attend the event
     * @param userID the user id of the attendee
     * @return true if and only if the attendee with the specified user id is attending the event
     */
    public boolean hasAttendee(UUID userID){
        return attendees.contains(userID);
    }

    /**
     * attempt to remove an attendee with a specified user id from the event
     * @param userID the user id of the specified attendee
     * @return true if and only if the user with the specified user id is removed from the event
     */
    public boolean removeAttendee(UUID userID){
        if (attendees.contains(userID)){
            attendees.remove(userID);
            return true;
        }
        return false;
    }

    /**
     * @return true if and only if the event is not at max capacity
     */
    public boolean hasSpace(){
        return this.currentNum() < this.capacity;
    }

    /**
     * @return true if and only if the event has an assigned room
     */
    public boolean assignedRoom(){
        return room != null;
    }

    /**
     * set the room of the event to the specified room
     * @param room the room to set the event location to
     */
    public void setRoom(UUID room) {
        this.room = room;
    }

    /**
     * remove the room assigned to the event if it exists so that the event has no specified room
     */
    public void removeRoom(){
        room = null;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setType(StringProperty type){
        this.type = type;
    }

    @Override
    public String toString() {
        String full;
        if (currentNum() < capacity) {
            full = "available";
        } else {
            full = "full";
        }

        return name.getValue()+" @"+ startTime + ", status: "+ attendees.size() + "/" + capacity + " " + full;
    }

    public StringProperty getStatus(){
        if ((LocalDateTime.now().compareTo(startTime)) >= 0) {
            return new SimpleStringProperty("past");
        }
        if (hasSpace()){
            return new SimpleStringProperty("available");
        }
        return new SimpleStringProperty("full");
    }

    public int getTotalPeople(){
        return attendees.size();
    }
}
