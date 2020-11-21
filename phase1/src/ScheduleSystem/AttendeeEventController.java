package ScheduleSystem;

import users.User;
import users.UserGateway;
import users.UserManager;
import user_controllers.InputValidityChecker;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A controller class that handles the attendee's event management.
 */
public class AttendeeEventController {
    protected User currentUser;
    protected EventManager em;
    protected UserManager um;
    private EventPresenter ep = new EventPresenter();
    private Scanner scanner = new Scanner(System.in);
    private EventGateway eg = new EventGateway();
    private UserGateway ug = new UserGateway();
    protected InputValidityChecker vc = new InputValidityChecker();

    /**
     * Initializes an AttendeeEventController
     * @param user the user associated.
     * @param um the user manager in the system.
     * @param em the event manager in the system.
     */
    public AttendeeEventController(User user, UserManager um, EventManager em) {
        currentUser = user;
        this.um = um;
        this.em = em;
    }

    /**
     * Executes the event management program for organizer, enabling different options.
     * @return true when the program exits.
     * @throws IOException when file reading or writing is interrupted.
     */
    public boolean attendeeRun() throws IOException {
        String[] validInputs = new String[]{"1", "2", "3"};
        while (true) {
            System.out.println("Please enter the number of corresponding choice: \n" +
                    "1. Sign Up for an event  \n" +
                    "2. View signed up events/Cancel \n" +
                    "3. Go back to the main menu");

            String input = vc.isValidInput(vc.validList(validInputs), scanner.nextLine());
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
            } else if (input.equals("3")){
                return true;
            }
        }
    }




    private boolean signUpForEvent() throws IOException{
        ArrayList<String> validInputEvents = getValidEventsNames();
        if (validInputEvents.size() == 0){
            ep.noAvailableEvents();
            return false;
        }
        if (!signUpOrGoBack()){
            return false;
        }
        ep.promptEvent();
        String input = vc.isValidInput(validInputEvents, scanner.nextLine());
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
        serializeInformation();
        ep.signUpSuccess(targetEvent.getName());
        if (em.getAvailableEventsForUser(currentUser).size() == 0){
            return false;
        }
        return signUpAgain();
    }

    private boolean viewAndCancelEvent() {
        ArrayList<String> validInputEvents = getValidCancelEventsNames();
        if (validInputEvents.size() == 0){
            ep.noAvailableEvents();
            return false;
        }
        if (!cancelOrGoBack()){
            return false;
        }
        ep.promptCancelEvent();
        String input = vc.isValidInput(validInputEvents, scanner.nextLine());
        Event targetEvent = em.getEventByName(input);
        try{
            em.removeUser(currentUser, targetEvent);
            um.removeEvent(targetEvent, currentUser);
        } catch (UnableToCancelException e) {
            ep.unableToCancelPrompt();
            return cancelAgain();
        }
        serializeInformation();
        ep.cancelSuccess(targetEvent.getName());
        if (em.getEventsByUser(currentUser).size() == 0){
            return false;
        }
        return cancelAgain();

    }

    private boolean signUpAgain(){
        ep.signUpAgainPrompt();
        String[] validInputs = new String[]{"1", "2"};
        String input = vc.isValidInput(vc.validList(validInputs), scanner.nextLine());
        return input.equals("1");
    }

    private boolean signUpOrGoBack() {
        ep.signUpOrGoBackPrompt();
        String[] validInputs = new String[]{"1", "2"};
        String input = vc.isValidInput(vc.validList(validInputs), scanner.nextLine());
        return input.equals("1");
    }

    private boolean cancelOrGoBack() {
        ep.cancelOrGoBackPrompt();
        String[] validInputs = new String[]{"1", "2"};
        String input = vc.isValidInput(vc.validList(validInputs), scanner.nextLine());
        return input.equals("1");
    }

    private boolean cancelAgain(){
        ep.cancelAgainPrompt();
        String[] validInputs = new String[]{"1", "2"};
        String input = vc.isValidInput(vc.validList(validInputs), scanner.nextLine());
        return input.equals("1");
    }

    private ArrayList<String> getValidEventsNames() {
        ArrayList<Event> validInputEvents = em.getAvailableEventsForUser(currentUser);
        ArrayList<String> validInputs = new ArrayList<>();
        for (Event e:validInputEvents){
            validInputs.add(e.getName());
        }
        return validInputs;
    }

    /**
     * Returns all the names of the events in the system.
     * @return an ArrayList of Strings of all event names.
     */
    public ArrayList<String> getAllEventsNames() {
        ArrayList<Event> validInputEvents = em.getEvents();
        ArrayList<String> validInputs = new ArrayList<>();
        for (Event e:validInputEvents){
            validInputs.add(e.getName());
        }
        return validInputs;
    }
    /**
     * Returns all the names of the rooms in the system.
     * @return an ArrayList of Strings of all room names.
     */
    protected ArrayList<String> getAllRoomsNames() {
        ArrayList<Room> validInputRooms = em.getRooms();
        ArrayList<String> validInputs = new ArrayList<>();
        for (Room r:validInputRooms){
            validInputs.add(r.getRoomName());
        }
        return validInputs;
    }

    private ArrayList<String> getValidCancelEventsNames() {
        ArrayList<Event> validInputEvents = em.getEventsByUser(currentUser);
        ArrayList<String> validInputs = new ArrayList<>();
        for (Event e:validInputEvents){
            validInputs.add(e.getName());
        }
        return validInputs;
    }

    private void serializeInformation(){
        eg.serializeEM("em.ser", em);
        ug.serializeUserManager("um.ser", um);
    }

}