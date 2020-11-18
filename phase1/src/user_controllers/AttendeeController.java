package user_controllers;

import ScheduleSystem.Event;
import ScheduleSystem.EventManager;
import signup_system.SignUpManager;
import users.User;
import signup_system.SignUpPresenter;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class AttendeeController {
    private User user;
    private Scanner scanner = new Scanner(System.in);
    private SignUpPresenter sup = new SignUpPresenter();
    private SignUpManager sum = new SignUpManager();
    private EventManager em = new EventManager();

    public AttendeeController(User thisUser){
        user = thisUser;
    }

    public void run(){
        ArrayList<String> validInputs = new ArrayList();
        validInputs.add("1");
        validInputs.add("2");
        validInputs.add("3");
        validInputs.add("4");

        boolean running = true;
        while (running){
            System.out.println("Please enter the number of corresponding choice: 1.SignUp for event  2.View/cancel signed up events 3.Message 4.Exit program");
            String input = isValidInput(validInputs, scanner.nextLine());

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
        String checkInput = newInput;

        while(!validInputs.contains(checkInput)){
            sup.notValidInput();
            checkInput = scanner.nextLine();
        }

        return checkInput;
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

        ArrayList<String> validInputs = new ArrayList();
        validInputs.add("1");
        validInputs.add("2");

        sup.signUpAgainPrompt();
        String confirm = this.isValidInput(validInputs, scanner.nextLine());
        return confirm.equals("1");
    }

    public boolean viewCancelEvents(){
        String event = sup.promptCancelEvent().toUpperCase();
        int result = sum.cancelUser(user, event);
        if (result == 0){
            sup.cancelFailure();
        }

        sup.cancelSuccess();

        ArrayList<String> validInputs = new ArrayList();
        validInputs.add("1");
        validInputs.add("2");

        sup.cancelAgainPrompt();
        String confirm = this.isValidInput(validInputs, scanner.nextLine());
        return confirm.equals("1");
    }

    public boolean message(User user){
        return true;
    }
}
