package ScheduleSystem;

import conference_entities.Room;
import users.User;

import java.io.*;
import java.util.*;

public class EventManager {
    private ArrayList<Event> events;
    private ArrayList<Room> rooms;
    public EventManager(){
        this.events = deserializeEvents();
        this.rooms = deserializeRooms();
    }

    private ArrayList<Event> deserializeEvents() {
        try {
            FileInputStream fileInputStream = new FileInputStream("phase1/src/ScheduleSystem/Events");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            ArrayList<Event> temp = (ArrayList<Event>) objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();
            return temp;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<Room> deserializeRooms() {
        try {
            FileInputStream fileInputStream = new FileInputStream("phase1/src/conference_entities/Rooms");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            ArrayList<Room> temp = (ArrayList<Room>) objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();
            return temp;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void serializeEvents(){
        try{
            FileOutputStream fileOutputStream = new FileOutputStream("phase1/src/ScheduleSystem/Events");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(events);

            objectOutputStream.close();
            fileOutputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void serializeRooms(){
        try{
            FileOutputStream fileOutputStream = new FileOutputStream("phase1/src/conference_entities/Rooms");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(rooms);

            objectOutputStream.close();
            fileOutputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean addEvent(Event event){
        if (this.events.contains(event)){
            return false;
        }
        else{
            this.events.add(event);
            serializeEvents();
            return true;
        }
    }

    public boolean addRoom(Room room){
        if (this.rooms.contains(room)){
            return false;
        }
        this.rooms.add(room);
        serializeRooms();
        return true;
        }

    public Event getEventByID(UUID eventID) throws NoEventFoundException{
        for (Event e:events){
            if (e.getId() == eventID){
                return e;
            }
        }
        throw new NoEventFoundException();
    }
    public ArrayList<Event> getEvents(){
        return this.events;
    }

    public boolean signUpUser(User user, Event event){
        for (UUID i : user.getEnrolledEvents()){
            try {
                Event ev = this.getEventByID(i);
                if (ev.getDate() == event.getDate()){
                    return false;
                }
            } catch (NoEventFoundException e) {
                e.printStackTrace();
                return false;
            }
        }
        event.addAttendee(user.getID());
        return true;
        }

    public boolean removeUser(User user, Event event){
        if (event.getAttendees().contains(user)){
            event.getAttendees().remove(user);
            return true;
        }
        else{
            return false;
        }
    }

    public ArrayList<Event> getEventsByUser(User user){
        ArrayList<Event> userEvents = new ArrayList<>();
        for (Event e:events){
            if (e.getAttendees().contains(user.getID())){
                userEvents.add(e);
            }
        }
        return userEvents;
    }


}
class NoEventFoundException extends Exception {
}