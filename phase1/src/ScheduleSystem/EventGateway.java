package ScheduleSystem;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

/**
 * EventGateway class contains functionality to serialize schedule system data.
 * Also contains functionality to intake data from outside the program.
 */
public class EventGateway {
    /**
     * Method that intakes a text file containing event data and returns an array list containing the spliced
     * contents of the text file. Each
     * element of the arraylist contains a string containing the data for one event.
     * @param eventFile a well formated textfile that contains event data
     * @return a arraylist containing the spliced eventFile
     * @throws FileNotFoundException
     */
    private ArrayList<String> eventsTextToArrayList(File eventFile) throws FileNotFoundException {
        ArrayList<String> eventsTextList = new ArrayList<>();
        Scanner textFileInput = new Scanner(eventFile);
        textFileInput.useDelimiter("\n");

        while(textFileInput.hasNext()){
            eventsTextList.add(textFileInput.next());
        }
        return eventsTextList;
    }

    /**
     * Method that intakes a well formatted string containing data for one event and returns an event object.
     * @param eventText well formatted string containing data for one event
     * @return an event object appropriate to the eventText
     * @throws ParseException
     */
    private Event eventStringToEvent(String eventText) throws ParseException {
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

    /**
     * Method that intakes an well formatted text file and returns an array list of event objects according
     * to the text file
     * @param EventsFile an text file of well formatted text containing the data for events.
     * @return an array list of events according to EventsText
     * @throws ParseException
     */
     public ArrayList<Event> eventsTextToEvents(File EventsFile) throws ParseException, FileNotFoundException {
        ArrayList<String> eventTextList = eventsTextToArrayList(EventsFile);

        ArrayList<Event> events = new ArrayList<>();
        for (String eventString : eventTextList){
            Event tempEvent = eventStringToEvent(eventString);
            events.add(tempEvent);
        }
        return events;
     }
    // TODO: 11/15/2020 make this class run on first startup of program. Since we assume also make sure eventManager is
    //  instantiated with the run of the gateway. (where is the eventManager instantiated?/check if it is)
}
