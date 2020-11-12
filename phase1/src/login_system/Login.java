package login_system;

import user_controllers.AttendeeController;
import user_controllers.OrganizerController;
import user_controllers.SpeakerController;
import users.User;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;


public class Login {

    private ArrayList<User> user;
    Scanner in = new Scanner(System.in);

    AttendeeController ac = new AttendeeController();
    OrganizerController oc = new OrganizerController();
    SpeakerController sc = new SpeakerController();

    public void readFromFile(String path) throws ClassNotFoundException{
        try{
            InputStream file = new FileInputStream(path);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            user = (ArrayList<User>) input.readObject();
            input.close();
        }catch (IOException ex){
            System.out.println("Cannot read from input");
        }
    }

    private void displayMenu(User user){
        if (user.getType().equals("Organizer")){
            System.out.println("Please enter the number of corresponding choise: 1.Enter room; 2.Create speaker account; 3. Schedule event");
            if(in.nextInt()==1){
                oc.enterRoom();
            }else if(in.nextInt()==2){
                oc.createSpeakerAccount();
            }else{
                oc.planEvent();
            }

        }else if (user.getType().equals("Speaker")){
            System.out.println("Please enter the number of corresponding choise: 1.View list of talks schedules; 2.Message attendees");
            if(in.nextInt()==1){
                sc.viewSchedulesTalks();
            }else{
                sc.messageAttendees();
            }
        }else{
            System.out.println("Please enter the number of corresponding choice: 1.Browse all events; 2.Sign up for an event; 3.See all signed up events; 4.Message");
            if (in.nextInt()==1){
                ac.signUp();
            }else if (in.nextInt()==2){
                ac.browseAllTalks();
            }else if(in.nextInt()==3){
                ac.browseSignedUpTalks();
            }
            else{
                ac.message();
            }
        }
    }

    private void loginAgain(){
        System.out.println("Incorrect email or password, please enter again!");
        login(promptEmail(),promptPassword());
    }

    private String promptEmail(){
        System.out.println("Please enter your username: ");
        return in.nextLine();
    }

    private String promptPassword(){
        System.out.println("Please enter your password: ");
        return in.nextLine();
    }

    private void login(String email, String pass){

        for (User value : user) {
            if (value.getEmail().equals(email)) {
                if (value.getPassword().equals(pass)){
                    displayMenu(value);
                }
            }
        }
        loginAgain();
    }

    public void main(String[] args) throws ClassNotFoundException {

        readFromFile("/users/UserManager.ser");

        login(promptEmail(),promptPassword());

    }


}