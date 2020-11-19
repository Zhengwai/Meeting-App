package ScheduleSystem;

import users.User;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

public class EventGateway {

    /** Assume that the text file parsed is well formatted **/

    /*public ArrayList<String> eventsTextToArrayList(File eventFile) throws FileNotFoundException {
        ArrayList<String> eventsTextList = new ArrayList<>();
        Scanner textFileInput = new Scanner(eventFile);
        textFileInput.useDelimiter("\n");

        while(textFileInput.hasNext()){
            eventsTextList.add(textFileInput.next());
        }
        return eventsTextList;
    }

    public Event eventStringToEvent(String eventText) throws ParseException {
       Scanner eventStringInput = new Scanner(eventText);
       eventStringInput.useDelimiter(",");
       DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy h:mm a");


       String eventName = eventStringInput.next().trim();
       String eventDateText = eventStringInput.next().trim();
       String eventCapacityText = eventStringInput.next().trim();

       Date eventDate = formatter.parse(eventDateText);
       int eventCapacity = Integer.parseInt(eventCapacityText);

        return new Event(eventName,eventDate,eventCapacity);

    }
     public ArrayList<Event> eventsTextToEvents(ArrayList<String> EventsText) throws ParseException {
        ArrayList<Event> events = new ArrayList<>();
        for (String eventString : EventsText){
            Event tempEvent = eventStringToEvent(eventString);
            events.add(tempEvent);
        }
        return events;
     }*/
    @SuppressWarnings("unchecked")
    public ArrayList<Event> deserializeEvents(String filePath) throws ClassNotFoundException{
        try {
            InputStream file = new FileInputStream(filePath);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            ArrayList<Event> events = (ArrayList<Event>) input.readObject();
            input.close();
            return events;
        } catch (IOException ex) {
            //TODO: Needs logger
            return new ArrayList<>();
        }
    }
    public void serializeEvents(String filePath, ArrayList<Event> events) throws IOException {
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(events);

            objectOutputStream.close();
            fileOutputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Room> deserializeRooms(String filePath) throws ClassNotFoundException{
        try {
            InputStream file = new FileInputStream(filePath);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            ArrayList<Room> rooms = (ArrayList<Room>) input.readObject();
            input.close();
            return rooms;
        } catch (IOException ex) {
            //TODO: Needs logger
            return new ArrayList<>();
        }


    }
    public void serializeRooms(String filePath, ArrayList<Room> rooms) throws IOException {
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(rooms);

            objectOutputStream.close();
            fileOutputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    // TODO: 11/15/2020 make this class run on first startup of program. Since we assume also make sure eventManager is
    //  instantiated with the run of the gateway. (where is the eventManager instantiated?/check if it is)


    
}
