package user_controllers;

import ScheduleSystem.Event;
import ScheduleSystem.EventManager;
import signup_system.SignUpManager;
import users.User;
import signup_system.SignUpPresenter;

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
            System.out.println("Please enter the number of corresponding choice: 1.SignUp for event  2.View/cancel signed up events 3.Message 4.Exit program");
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
                    r = message(user);
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
        for (Event e:allEvents){
            System.out.println(e);
        }
    }

    public void browseSignedUpTalks(){
        ArrayList<Event> allEvents = em.getEventsByUser(user);
        for (Event e:allEvents){
            System.out.println(e);
        }
    }

    public boolean signUp(){
        String event = sup.promptEvent().toUpperCase();
        int result = sum.signUserUp(this.user, event);

        if(result == 0){
            sup.signUpFailure();
        }

        if(result == 1){
            sup.eventFull();
        }

        if(result == 2){
            sup.alreadySignedUp();
        }

        if(result == 3){
            sup.signUpSuccess(event);
        }

        if(result == 4){
            sup.eventDateConflict();
        }

        sup.signUpAgainPrompt();
        String confirm = isValidInput(validList(validYN), scanner.nextLine());
        return confirm.equals("Y");
    }

    public boolean viewCancelEvents(){
        String event = sup.promptCancelEvent().toUpperCase();
        int result = sum.cancelUser(user, event);
        if (result == 0){
            sup.cancelFailure();
        }

        sup.cancelSuccess();

        sup.cancelAgainPrompt();
        String confirm = isValidInput(validList(validYN), scanner.nextLine());
        return confirm.equals("Y");
    }

    public boolean message(User user){
        return true;
    }
}
