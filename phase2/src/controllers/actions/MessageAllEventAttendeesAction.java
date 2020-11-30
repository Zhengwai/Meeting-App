package controllers.actions;

import entities.Event;
import entities.Speaker;
import entities.User;
import use_cases.ConversationManager;
import use_cases.EventManager;
import use_cases.UserManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.UUID;

public class MessageAllEventAttendeesAction extends MessageAllAction {
    private EventManager em;

    public MessageAllEventAttendeesAction(UUID userID, UserManager um, ConversationManager cm, EventManager em) {
        super(userID, um, cm);
        this.em = em;
    }

    public void run() throws IOException {
        try {
            Speaker user = (Speaker) um.getUserByID(userID);
            ArrayList<Event> events = em.getEventsBySpeaker(user);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            if(events.size() > 0) {
                System.out.println("Choose one of your following talks to message:");
                int k = 0;
                for (Event e : events) {
                    System.out.println(k + ". " + e.getName());
                    k++;
                }

                String inp = br.readLine();
                Event evt = events.get(Integer.parseInt(inp));
                ArrayList<UUID> attendants = evt.getAttendees();
                ArrayList<User> attendeesUser = new ArrayList<>();

                for (int i = 0; i < attendants.size(); i++) {
                    attendeesUser.add(um.getUserByID(attendants.get(i)));
                }

                handleMessageAll(attendeesUser);
            }
            else{
                System.out.println("You are not set to speak at any event.");
            }
        } catch (IOException e) {
            System.out.println("Failed to read input.");
        }
    }

    public String getName() {
        return "Message All Attendees of an Event";
    }
}
