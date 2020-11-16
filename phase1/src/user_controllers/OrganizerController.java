package user_controllers;

import ScheduleSystem.Event;
import ScheduleSystem.EventManager;
import conference_entities.Room;
import signup_system.SignUpManager;
import signup_system.SignUpPresenter;
import users.Speaker;
import users.User;
import users.UserManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Date;

public class OrganizerController {
    private Scanner scanner = new Scanner(System.in);
    private User user;
    private SignUpPresenter sup = new SignUpPresenter();
    private SignUpManager sum = new SignUpManager();
    private EventManager em = new EventManager();
    private UserManager um = new UserManager();

    public OrganizerController(User user){
        this.user = user;
    }

    public void menu(){

    }

    public String planEvent() {
        System.out.println("What year would like the event to take place? (Enter any year after 1900)");
        int year = scanner.nextInt() - 1900;
        System.out.println("What month would like the event to take place? (Enter any month from (1-12)");
        int month = scanner.nextInt() - 1;
        System.out.println("What day would like the event to take place? (Enter any valid day within the month, ranging from 1-31");
        int day = scanner.nextInt();
        System.out.println("Enter the hour you want the event to take place (0 - 23)");
        int hour = scanner.nextInt();
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day, hour, 0);
        Date date = cal.getTime();
        System.out.println("What would you like to call this event?");
        String eventName = scanner.nextLine();
        System.out.println("What would the capacity of the event be?");
        int capacity = scanner.nextInt();
        System.out.println("You are about to add an event called " + eventName + " on " + date.toString() + ", enter A to confirm and add this event, enter B to start over, enter C to go back to the main menu");
        String confirm = scanner.nextLine();
        if (confirm.equals("A")) {
            Event newEvent = new Event(eventName, date, capacity);
            if (!em.addEvent(newEvent)) {
                System.out.println("Same event already exists, would you like to try again? Enter Y to try again and N to go back to main menu");
                String tryAgain = scanner.nextLine();
                if (tryAgain.equals("Y")) {
                    return "B";
                }
                return "C";
            }
            System.out.println("Successfully added event, enter Y to add another one, enter N to go back to the main menu");
            String ynString = scanner.nextLine();
            if (ynString.equals("Y")) {
                return "B";
            }
            return "C";
        } else if (confirm.equals("B")) {
            return "B";
        }
        return "C";
    }

    public boolean enterRoom(){
        System.out.println("Please enter the name of the room");
        String roomName = scanner.nextLine();
        System.out.println("What would be the capacity of the room?");
        int roomCapacity = scanner.nextInt();
        System.out.println("You are about to add a room called "+roomName+" with a capacity of "+String.valueOf(roomCapacity)+". Enter A to confirm and add this event, enter B to start over, enter C to go back to main menu.");
        String confirm = scanner.nextLine();
        if (confirm.equals("A")){
            Room room = new Room(roomCapacity,roomName);
            if(!em.addRoom(room)) {
                System.out.println("Same room already exists, would you like to try again? Enter Y to try again and N to go back to main menu");
                String tryAgain = scanner.nextLine();
                if (tryAgain.equals("Y")) {
                    return true;
                }
                return false;
            }
            System.out.println("Successfully added room, enter Y to add another one, enter N to go back to the main menu");
            String ynString = scanner.nextLine();
            if (ynString.equals("Y")) {
                return true;
            }
            return false;
        } else if (confirm.equals("B")) {
            return true;
        }
        return false;
        }


    public boolean createSpeakerAccount() throws Exception {
        System.out.println("What would be the username for this speaker?");
        String username = scanner.nextLine();
        System.out.println("What would be the password for this speaker?");
        String password = scanner.nextLine();
        System.out.println("You are about to create a speaker account with username "+username+" with password "+password +". Enter A to confirm and add this account, enter B to start over, enter C to go back to main menu.");
        String confirm = scanner.nextLine();
        if (confirm.equals("A")){
            Speaker newSpeaker = new Speaker(username, password);
            if(!um.addUser(newSpeaker)) {
                System.out.println("Same account already exists, would you like to try again? Enter Y to try again and N to go back to main menu");
                String tryAgain = scanner.nextLine();
                if (tryAgain.equals("Y")) {
                    return true;
                }
                return false;
            }
            System.out.println("Successfully added account, enter Y to add another one, enter N to go back to the main menu");
            String ynString = scanner.nextLine();
            if (ynString.equals("Y")) {
                return true;
            }
            return false;
        } else if (confirm.equals("B")) {
            return true;
        }
        return false;
    }
    }


