package controllers;

import controllers.actions.MessageAction;
import controllers.actions.MessageActionClient;
import use_cases.ConversationManager;
import use_cases.EventManager;
import use_cases.UserManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.UUID;

public class MessageController {
    private ConversationManager cm;
    private UserManager um;
    private UUID userID;
    private EventManager em;

    public MessageController(UUID userID) throws ClassNotFoundException {
        //TODO: Need to fetch appropriate data from database (when it's ready of course).
        this.cm = new ConversationManager();
        this.um = new UserManager();
        this.userID = userID;
        this.em = new EventManager();
    }

    public void run() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        MessageActionClient mac = new MessageActionClient(userID, um, cm, em); // mac wit da scope haha brrr
        try {
            String input;

            do {
                List<MessageAction> actions = mac.getUserActions();

                //TODO: Handle in message presenter?
                System.out.println("Please Enter Corresponding Choice: \n");
                for (int i = 0; i < actions.size(); i++) {
                    System.out.println((i + 1) + ". " + actions.get(i).getName());
                }
                System.out.println("'exit' to leave the messaging system.");
                input = br.readLine();

                if (input.matches("^[0-9]$")) {
                    int idx = Integer.parseInt(input);

                    if (0 < idx && idx < actions.size() + 1) {
                        actions.get(idx - 1).run();
                    }

                } else if (!input.equals("exit")) {
                    System.out.println("Invalid input!");
                } else {
                    System.out.println("Exiting");
                }

            } while (!input.equals("exit"));

        } catch (IOException e) {
            System.out.println("Failed to read input.");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
