package ScheduleSystem;

import users.Speaker;
import users.User;
import users.UserManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class AttendeeEventController {
    private User currentUser;
    private EventManager em = new EventManager();
    private EventPresenter ep = new EventPresenter();
    private Scanner scanner = new Scanner(System.in);
    private UserManager um = new UserManager();
    public AttendeeEventController(User user) throws ClassNotFoundException {
        currentUser = user;
    }

    public boolean attendeeRun() throws IOException {
        boolean running = true;
        String[] validInputs = new String[]{"1", "2"};
        while (running) {
            System.out.println("Please enter the number of corresponding choice: 1.SignUp for event  2.View signed up events/Cancel 3.Go back to the main menu.");
            String input = isValidInput(validList(validInputs), scanner.nextLine());
            boolean r = true;
            if (input.equals("1")) {
                ArrayList<Event> availableEvents = em.getAvailableEventsForUser(currentUser);
                if (availableEvents.size() == 0){
                    ep.noAvailableEvents();
                    r = false;
                }
                while (r) {
                    availableEvents = em.getAvailableEventsForUser(currentUser);
                    ep.introduceEventsSignUp();
                    ep.showEvents(availableEvents);
                    r = signUpForEvent();
                }
            } else if (input.equals("2")) {
                ArrayList<Event> signedUpEvents = em.getEventsByUser(currentUser);
                if (signedUpEvents.size() == 0){
                    ep.noEventsToCancel();
                    r = false;
                }
                while (r) {
                    signedUpEvents = em.getEventsByUser(currentUser);
                    ep.introduceEventsCancel();
                    ep.showEvents(signedUpEvents);
                    r = viewAndCancelEvent();
                }
            } else {
                running = false;
            }
        }
        return true;
    }




    protected boolean signUpForEvent() throws IOException{
        ArrayList<String> validInputEvents = getValidEventsNames();
        if (validInputEvents.size() == 0){
            ep.noAvailableEvents();
            return false;
        }
        if (!signUpOrGoBack()){
            return false;
        }
        ep.promptEvent();
        String input = isValidInput(validList(validInputEvents), scanner.nextLine());
        Event targetEvent = em.getEventByName(input);
        try{
            em.signUpUser(currentUser, targetEvent);
            um.addEventForUser(targetEvent, currentUser);
        } catch (AlreadySignedUpException e) {
            ep.alreadySignedUp();
            return signUpAgain();
        } catch (TimeConflictException e) {
            ep.timeConflict();
            return signUpAgain();
        }
        ep.signUpSuccess(targetEvent.getName());
        if (em.getAvailableEventsForUser(currentUser).size() == 0){
            return false;
        }
        return signUpAgain();

    }

    protected boolean viewAndCancelEvent() {
        ArrayList<String> validInputEvents = getValidCancelEventsNames();
        if (validInputEvents.size() == 0){
            ep.noAvailableEvents();
            return false;
        }
        if (!cancelOrGoBack()){
            return false;
        }
        ep.promptCancelEvent();
        String input = isValidInput(validList(validInputEvents), scanner.nextLine());
        Event targetEvent = em.getEventByName(input);
        try{
            em.removeUser(currentUser, targetEvent);
            um.removeEvent(targetEvent, currentUser);
        } catch (UnableToCancelException | IOException e) {
            ep.unableToCancelPrompt();
            return cancelAgain();
        }
        ep.cancelSuccess(targetEvent.getName());
        if (em.getEventsByUser(currentUser).size() == 0){
            return false;
        }
        return cancelAgain();

    }

    protected boolean signUpAgain(){
        ep.signUpAgainPrompt();
        String[] validInputs = new String[]{"1", "2"};
        String input = isValidInput(validList(validInputs), scanner.nextLine());
        return input.equals("1");
    }

    protected boolean signUpOrGoBack() {
        ep.signUpOrGoBackPrompt();
        String[] validInputs = new String[]{"1", "2"};
        String input = isValidInput(validList(validInputs), scanner.nextLine());
        return input.equals("1");
    }

    protected boolean cancelOrGoBack() {
        ep.cancelOrGoBackPrompt();
        String[] validInputs = new String[]{"1", "2"};
        String input = isValidInput(validList(validInputs), scanner.nextLine());
        return input.equals("1");
    }

    protected boolean cancelAgain(){
        ep.cancelAgainPrompt();
        String[] validInputs = new String[]{"1", "2"};
        String input = isValidInput(validList(validInputs), scanner.nextLine());
        return input.equals("1");
    }

    protected ArrayList<String> getValidEventsNames() {
        ArrayList<Event> validInputEvents = em.getAvailableEventsForUser(currentUser);
        ArrayList<String> validInputs = new ArrayList<>();
        for (Event e:validInputEvents){
            validInputs.add(e.getName());
        }
        return validInputs;
    }
    protected ArrayList<String> getAllEventsNames() {
        ArrayList<Event> validInputEvents = em.getEvents();
        ArrayList<String> validInputs = new ArrayList<>();
        for (Event e:validInputEvents){
            validInputs.add(e.getName());
        }
        return validInputs;
    }

    protected ArrayList<String> getAllRoomsNames() {
        ArrayList<Room> validInputRooms = em.getRooms();
        ArrayList<String> validInputs = new ArrayList<>();
        for (Room r:validInputRooms){
            validInputs.add(r.getRoomName());
        }
        return validInputs;
    }

    protected ArrayList<String> getValidCancelEventsNames() {
        ArrayList<Event> validInputEvents = em.getEventsByUser(currentUser);
        ArrayList<String> validInputs = new ArrayList<>();
        for (Event e:validInputEvents){
            validInputs.add(e.getName());
        }
        return validInputs;
    }

    protected String isValidInput(ArrayList<String> validInputs, String newInput) {
        String checkInput = newInput.toUpperCase();

        while (!validInputs.contains(checkInput)) {
            ep.notValidInput();
            checkInput = scanner.nextLine();
        }

        return checkInput;
    }

    protected ArrayList<String> validList(String[] allValid) {
        return new ArrayList<>(Arrays.asList(allValid));
    }

    protected ArrayList<String> validList(ArrayList<String> allValid) {
        return allValid;
    }

}