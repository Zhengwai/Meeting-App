package controllers;


import entities.Conversation;
import use_cases.EventManager;
import entities.User;
import use_cases.UserManager;
import entities.Event;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Controller class.
 * A subclass of the AttendeeMessageController.
 * Similar in functionality to AttendeeMessageController only it supports messaging all attendees in an event.
 */
public class SpeakerMessageController extends AttendeeMessageController {

    /**
     * Creates a new instance of OrganizerMessageController using its parent's constructor.
     * @param inpUser the current user logged in
     * @param um a reference of the deserialized UserManager
     * @param em a reference of the deserialized EventManager
     */
    public SpeakerMessageController(User inpUser, UserManager um, EventManager em) {
        super(inpUser, um, em);
    }

    /**
     * This method is to be called immediately after SpeakerMessageController is instantiated.
     * Prompts the user with different options for interaction with the message system.
     * Includes the message to all Attendees functionality.
     * Serializes the <code>ConversationManager</code> to <code>cm.ser</code>.
     */
    public void run() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            String input = "";
            while (!input.equals("exit")) {
                System.out.println("Please Enter Corresponding Choice: \n " +
                        "1. Add Friend \n " +
                        "2. Message Existing Conversation \n" +
                        "3. New Message to all attendees of a talk \n" +
                        "exit to exit this Controller");
                input = br.readLine();

                switch(input) {
                    case "1":
                        System.out.println("Enter the username of the person you want to add");
                        input = br.readLine();
                        handleAddFriend(input);
                        break;

                    case "2":
                        /*ArrayList<Conversation> conversations = cm.getConversations(this.user.getConversations());
                        System.out.println(mp.promptMainScreen(conversations));
                        System.out.println("Enter the number of the conversation to open:");
                        input = br.readLine();
                        handleConversations(input, conversations);
                        break;*/

                    case "3":
                        handleMessageAllAttendees();
                        break;

                    default:
                        if (!input.equals("exit")) {
                            System.out.println("Chose invalid option");
                        }
                        break;
                }

            }
            serializeCM();
        } catch (IOException e) {
            System.out.println("Failed to read input");
            e.printStackTrace();
        }

    }

    /*public void handleMessageAll(ArrayList<User> users) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter your Message");
            String inp = br.readLine();
            Message msg = new Message(user.getID(), inp);

            UUID conID = this.cm.newConversation();
            Conversation c = this.cm.getConversation(conID);
            c.addMember(user.getID());
            c.setOwner(user.getID());
            user.addConversation(conID);
            for (int i = 0; i < users.size(); i++) {
                c.addMember(users.get(i).getID());
                users.get(i).addConversation(conID);
            }

            System.out.println("Enter your message title");
            inp = br.readLine();
            c.setName(inp);
            c.sendMessage(msg);
        } catch (IOException e) {
            System.out.println("Failed to read input.");
        }
    }*/

    /**
     * Helper method for the <code>run()</code> method.
     */
    public void handleMessageAllAttendees() {
        try {
            ArrayList<Event> events = em.getEventsByUser(user);
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

    private void serializeCM() {
        try {
            this.cg.saveToFile("cm.ser", this.cm);
        } catch (IOException e) {
            System.out.println("Something went wrong.");
        }
    }
}