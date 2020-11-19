package user_controllers;

import ScheduleSystem.Event;
import ScheduleSystem.EventManager;
import message_system.AttendeeMessageController;
import message_system.Message;
import message_system.MessageController;
import signup_system.SignUpManager;
import users.User;
import signup_system.SignUpPresenter;
import users.UserGateway;
import users.UserManager;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class AttendeeController {
    private User user;
    private Scanner scanner = new Scanner(System.in);
    private SignUpPresenter sup = new SignUpPresenter();
    private SignUpManager sum = new SignUpManager();
    private EventManager em = new EventManager();
    private String[] validYN = new String[]{"Y", "N"};

    public AttendeeController(User thisUser){
        user = thisUser;
    }

    public void run(){
        String[] valid = new String[]{"1", "2", "3", "4"};

        boolean running = true;
        while (running){
            System.out.println("Please enter the corresponding number for your choice: \n" +
                    "1. Sign up for an event\n" +
                    "2. View/cancel signed up events\n" +
                    "3. Message\n" +
                    "4. Exit program");

            String input = isValidInput(validList(valid), scanner.nextLine());

            if (input.equals("1")){
                boolean r = true;
                while (r){
                    browseAllTalks();
                    r = signUp();
                }
            } else if (input.equals("2")){
                boolean r = true;
                while (r){
                    browseSignedUpTalks();
                    r = viewCancelEvents();
                }
            } else if (input.equals("3")){
                boolean r = true;
                while (r){
                    r = message();
                }
            } else {
                running = false;
            }
        }

    }

    private String isValidInput(ArrayList<String> validInputs, String newInput){
        String checkInput = newInput.toUpperCase();

        while(!validInputs.contains(checkInput)){
            sup.notValidInput();
            checkInput = scanner.nextLine();
        }

        return checkInput;
    }

    private ArrayList<String> validList(String[] allValid){
        ArrayList<String> validInputs = new ArrayList<String>(Arrays.asList(allValid));
        return validInputs;
    }

    public void browseAllTalks(){
        ArrayList<Event> allEvents = em.getEvents();
        sup.showEvents(allEvents);
    }

    public void browseSignedUpTalks(){
        ArrayList<Event> allEvents = em.getEventsByUser(user);
        sup.showEvents(allEvents);
    }

    public boolean signUp(){
        sup.promptEvent();
        String event = scanner.nextLine().toUpperCase();
        sum.signUserUp(user, event);

        sup.signUpAgainPrompt();
        String confirm = isValidInput(validList(validYN), scanner.nextLine());
        return confirm.equals("Y");
    }

    public boolean viewCancelEvents(){
        sup.promptCancelEvent();
        String event = scanner.nextLine().toUpperCase();
        int result = sum.cancelUser(user, event);
        if (result == 0){
            sup.cantFindEvent();
        }

        sup.cancelSuccess();

        sup.cancelAgainPrompt();
        String confirm = isValidInput(validList(validYN), scanner.nextLine());
        return confirm.equals("Y");
    }

    public boolean message(){
        UserGateway ug = new UserGateway();
        UserManager um = new UserManager();

        try {
            um = ug.readFromFile("/phase1/userManager.ser");
        } catch (Exception e) {
            System.out.println("Something went wrong.");
        }

        AttendeeMessageController amc = new AttendeeMessageController(this.user, um);
        amc.run();
        return true;
    }
}
