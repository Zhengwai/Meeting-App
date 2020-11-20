package ScheduleSystem;

import users.User;
import users.UserManager;

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
    public EventManager deserializeEM(String filePath) throws ClassNotFoundException{
        try {
            InputStream file = new FileInputStream(filePath);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            EventManager em = (EventManager) input.readObject();
            input.close();
            return em;
        } catch (IOException ex) {
            return new EventManager();
        }
    }
    public void serializeEM(String filePath, EventManager em) {
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(em);

            objectOutputStream.close();
            fileOutputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    }
    // TODO: 11/15/2020 make this class run on first startup of program. Since we assume also make sure eventManager is
    //  instantiated with the run of the gateway. (where is the eventManager instantiated?/check if it is)


    

