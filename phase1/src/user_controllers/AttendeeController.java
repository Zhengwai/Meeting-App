package user_controllers;

import signup_system.SignUpManager;
import users.User;
import signup_system.SignUpPresenter;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class AttendeeController {
    private User user;
    private SignUpPresenter sup = new SignUpPresenter();
    private SignUpManager sum = new SignUpManager();

    public AttendeeController(User thisUser){
        user = thisUser;
    }

    public void browseAllTalks(){
        try {
            ArrayList<String> allEvents = compileEvents();
            sup.showEvents(allEvents);
        }

        catch(FileNotFoundException ex){
            System.out.println("File cannot be found");
        }
    }

    private ArrayList<String> compileEvents() throws FileNotFoundException{
        File allEvents = new File("phase1/src/signup_system/EventList");
        Scanner scan = new Scanner(allEvents);
        ArrayList<String> allEventLines = new ArrayList<>();

        while (scan.hasNextLine()) {
            allEventLines.add(scan.nextLine());
        }

        return allEventLines;
    }

    public void signUp(){
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
    }

    public void browseSignedUpTalks(){

    }

    public void message(){


    }
}
