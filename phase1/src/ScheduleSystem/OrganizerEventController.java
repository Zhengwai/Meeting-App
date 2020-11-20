package ScheduleSystem;

import sun.security.util.ArrayUtil;
import users.Speaker;
import users.User;
import users.UserManager;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OrganizerEventController extends AttendeeEventController{
    private User currentUser;
    private EventManager em = new EventManager();
    private EventPresenter ep = new EventPresenter();
    private OrganizerEventPresenter oep = new OrganizerEventPresenter();
    private Scanner scanner = new Scanner(System.in);
    private UserManager um = new UserManager();
    public OrganizerEventController(User user) throws ClassNotFoundException {
        super(user);
    }

    public boolean organizerRun() throws IOException, AlreadySignedUpException, TimeConflictException {
        boolean running = true;
        String[] validInputs = new String[]{"1", "2","3","4"};
        while (running) {
            System.out.println("Please enter the number of corresponding choice: \n" +
                    "1.Create Room \n  " +
                    "2.Create Event \n" +
                    "3.Assign Room \n" +
                    "4.Assign Speaker\n" +
                    "5.Exit");
            String input = isValidInput(validList(validInputs), scanner.nextLine());
            boolean r = true;
            if (input.equals("1")) {
                while (r) {
                    r = createRoom();
                }
            } else if (input.equals("2")) {

                while (r) {
                    r = createEvent();
                    }

            } else if (input.equals("3")){
                while (r){
                    r = assignRoom();
                }
            } else if (input.equals("4")) {
                while (r){
                    r = assignSpeaker();
                }
            } else {
                running = false;
            }
        }
        return true;
    }

    private boolean createEvent() throws IOException {
        String [] validYN = new String[]{"1", "2"};
        if (!createEventUpOrGoBack()){
            return false;
        }
        oep.yearPrompt();
        int year = isValidYearInput(scanner.nextInt());
        oep.monthPrompt();
        int month = isValidMonthInput(scanner.nextInt());
        oep.dayPrompt();
        int day = isValidDayInput(scanner.nextInt(), year, month);
        oep.hourPrompt();
        int hour = isValidHourInput(scanner.nextInt());
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day, hour, 0, 0);
        Date date = cal.getTime();
        oep.eventNamePrompt();
        String eventName = scanner.nextLine();
        oep.eventConfirmPrompt(eventName, date);
        String confirm = isValidInput(validList(validYN), scanner.nextLine());
        if (confirm.equals("2")){
            return true;
        }
        Event event = new Event(eventName, date, 2);
        em.addEvent(event);
        oep.eventSuccessfullyAddedPrompt();
        return createEventAgain();
    }

    public boolean createRoom() throws IOException {
        String [] validYN = new String[]{"1", "2"};
        if (!createRoomOrGoBack()){
            return false;
        }
        oep.roomNamePrompt();
        String roomName = scanner.nextLine();
        oep.roomCapacityPrompt();
        int capacity = isValidCapacityInput(scanner.nextInt());
        oep.confirmRoomPrompt(roomName, capacity);
        String confirm = isValidInput(validList(validYN), scanner.nextLine());
        if (confirm.equals("2")){
            return true;
        }
        Room newRoom = new Room(capacity, roomName);
        em.addRoom(newRoom);
        oep.roomSuccessfullyAddedPrompt();
        return createRoomAgain();
    }

    public boolean assignRoom() throws IOException {
        String [] validYN = new String[]{"1", "2"};
        ArrayList<String> validInputEvents = getAllEventsNames();
        ArrayList<String> validInputRooms = getAllRoomsNames();
        if (validInputEvents.size() == 0){
            ep.noAvailableEvents();
            return false;
        }
        if (validInputRooms.size() == 0){
            ep.noAvailableRoomPrompt();
            return false;
        }
        if (!assignRoomOrGoBack()){
            return false;
        }
        ep.showEvents(em.getEvents());
        ep.promptEvent();
        String inputEvent = isValidInput(validList(validInputEvents), scanner.nextLine());
        Event targetEvent = em.getEventByName(inputEvent);

        if (targetEvent.assignedRoom()){
            oep.eventAlreadyHasRoom(em.getRoomByID(targetEvent.getRoom()).getRoomName());
            String inputYN = isValidInput(validList(validYN), scanner.nextLine());
            if (inputYN.equals("2")){
                return true;
            }
        }

        ep.showRooms(em.getRooms());
        oep.promptRoom();
        String inputRoom = isValidInput(validList(validInputRooms), scanner.nextLine());
        Room targetRoom = em.getRoomByName(inputRoom);
        if (!em.roomAvailableForEvent(targetRoom, targetEvent)){
            oep.timeConflictRoomAssignment();
            return assignRoomAgain();
        }
        oep.confirmAssignRoomPrompt(targetRoom.getRoomName(), targetEvent.getName());
        String confirm = isValidInput(validList(validYN), scanner.nextLine());
        if (confirm.equals("2")){
            return true;
        }
        em.assignRoom(targetRoom, targetEvent);
        oep.roomSuccessfullyAssigned();
        return assignRoomAgain();



    }

    public boolean assignSpeaker() throws IOException, AlreadySignedUpException, TimeConflictException {
        String [] validYN = new String[]{"1", "2"};
        ArrayList<String> validInputEvents = getAllEventsNames();
        ArrayList<String> validInputSpeakers = um.getAllSpeakerNames();
        if (validInputEvents.size() == 0){
            ep.noAvailableEvents();
            return false;
        }
        if (validInputSpeakers.size() == 0){
            oep.noAvailableSpeakerPrompt();
            return false;
        }
        if (!assignSpeakerOrGoBack()){
            return false;
        }

        ep.showEvents(em.getEvents());
        ep.promptEvent();
        String inputEvent = isValidInput(validList(validInputEvents), scanner.nextLine());
        Event targetEvent = em.getEventByName(inputEvent);

        if (targetEvent.existsSpeaker()){
            oep.eventAlreadyHasSpeaker(um.getUserByID(targetEvent.getSpeaker()).getUsername());
            String inputYN = isValidInput(validList(validYN), scanner.nextLine());
            if (inputYN.equals("2")){
                return true;
            }
        }
        oep.showSpeakers(um.getAllSpeakers());
        oep.promptSpeaker();
        String inputSpeaker = isValidInput(validList(validInputSpeakers), scanner.nextLine());
        Speaker targetSpeaker = (Speaker) um.getUserByName(inputSpeaker);
        if (!um.userAvailableForEvent(targetSpeaker, targetEvent)){
            oep.timeConflictSpeakerAssignment();
            return assignSpeakerAgain();
        }
        oep.confirmAssignSpeakerPrompt(targetSpeaker.getUsername(), targetEvent.getName());
        String confirm = isValidInput(validList(validYN), scanner.nextLine());
        if (confirm.equals("2")){
            return true;
        }
        um.addEventForUser(targetEvent, targetSpeaker);
        em.signUpUser(targetSpeaker, targetEvent);
        oep.speakerSuccessfullyAssigned();
        return assignSpeakerAgain();
    }
    protected boolean assignSpeakerAgain() {
        oep.assignSpeakerAgainPrompt();
        String[] validInputs = new String[]{"1", "2"};
        String input = isValidInput(validList(validInputs), scanner.nextLine());
        return input.equals("1");
    }
    protected boolean assignSpeakerOrGoBack() {
        oep.assignSpeakerOrGoBackPrompt();
        String[] validInputs = new String[]{"1", "2"};
        String input = isValidInput(validList(validInputs), scanner.nextLine());
        return input.equals("1");}

    protected boolean assignRoomOrGoBack() {
        oep.assignRoomOrGoBackPrompt();
        String[] validInputs = new String[]{"1", "2"};
        String input = isValidInput(validList(validInputs), scanner.nextLine());
        return input.equals("1");}

    protected boolean assignRoomAgain(){
        oep.assignRoomAgainPrompt();
        String[] validInputs = new String[]{"1", "2"};
        String input = isValidInput(validList(validInputs), scanner.nextLine());
        return input.equals("1");
    }
    protected boolean createEventAgain(){
        oep.createEventAgainPrompt();
        String[] validInputs = new String[]{"1", "2"};
        String input = isValidInput(validList(validInputs), scanner.nextLine());
        return input.equals("1");
    }

    protected boolean createEventUpOrGoBack() {
        oep.createEventOrGoBackPrompt();
        String[] validInputs = new String[]{"1", "2"};
        String input = isValidInput(validList(validInputs), scanner.nextLine());
        return input.equals("1");
    }
    protected boolean createRoomOrGoBack() {
        oep.createRoomOrGoBackPrompt();
        String[] validInputs = new String[]{"1", "2"};
        String input = isValidInput(validList(validInputs), scanner.nextLine());
        return input.equals("1");
    }
    protected boolean createRoomAgain(){
        oep.createRoomAgainPrompt();
        String[] validInputs = new String[]{"1", "2"};
        String input = isValidInput(validList(validInputs), scanner.nextLine());
        return input.equals("1");
    }


    protected int isValidYearInput(int newInput) {
        while (newInput <= 1900) {
            ep.notValidInput();
            newInput = scanner.nextInt();
        }

        return newInput;
    }

    protected int isValidMonthInput(int newInput) {
        while (newInput < 1 | newInput > 12){
            newInput = scanner.nextInt();
        }


        return newInput;
    }

    protected int isValidDayInput(int newInput, int year, int month) {
        if (month == 1|month ==3|month == 5|month == 7|month==8|month==10|month==12) {
            while (newInput < 1 | newInput > 31) {
                newInput = scanner.nextInt();
            }
        } else if (month != 2){
            while (newInput < 1 | newInput > 30) {
                newInput = scanner.nextInt();
            }

        } else if (year % 4 != 0) {
            while (newInput < 1 | newInput > 28) {
                newInput = scanner.nextInt();
            }
        } else {
            while (newInput < 1 | newInput > 29) {
                newInput = scanner.nextInt();
            }
        }


        return newInput;
    }

    protected int isValidHourInput(int newInput) {
        while (newInput < 9 | newInput > 16){
            newInput = scanner.nextInt();
        }


        return newInput;
    }

    protected int isValidCapacityInput(int newInput) {
        while (newInput <= 1){
            newInput = scanner.nextInt();
        }


        return newInput;
    }
}
