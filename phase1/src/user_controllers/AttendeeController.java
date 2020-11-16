package user_controllers;

import ScheduleSystem.Event;
import ScheduleSystem.EventManager;
import signup_system.SignUpManager;
import users.User;
import signup_system.SignUpPresenter;

import java.io.*;
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
        boolean running = true;
        while (running){
            System.out.println("Please enter the number of corresponding choice: 1.SignUp for event  2.View/cancel signed up events 3.Message 4.Exit program");
            int input = scanner.nextInt();
            if (input == 1){
                boolean r = true;
                while (r){
                    browseAllTalks();
                    r = signUp();
                }

            } else if (input == 2){
                boolean r = true;
                while (r){
                    browseSignedUpTalks();
                    r = viewCancelEvents();
                }
            } else if (input == 3){
                boolean r = true;
                while (r){
                    r = message(user);
                }

            } else {
                running = false;
            }
        }

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
        int result = sum.signUserUp(user, event);

        if(result == 0){
            sup.signUpFailure();
        }

        if(result == 1){
            sup.eventFull();
        }

        if(result == 2){
            sup.signUpSuccess(event);
        }

        if(result == 3){
            sup.alreadySignedUp();
        }
        System.out.println("Would you like to sign up for another event, type 1 for yes, 2 for no");
        int confirm = scanner.nextInt();
        return confirm == 1;
    }

    public boolean viewCancelEvents(){
        String event = sup.promptCancelEvent().toUpperCase();
        int result = sum.cancelUser(user, event);
        if (result == 0){
            sup.cancelFailure();
        }

        sup.cancelSuccess();
        System.out.println("Would you like to cancel another event, type 1 for yes, 2 for no");
        int confirm = scanner.nextInt();
        return confirm == 1;
    }

    public boolean message(User user){
        return true;
    }
}
