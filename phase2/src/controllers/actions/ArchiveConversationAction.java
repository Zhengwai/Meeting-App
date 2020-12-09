package controllers.actions;

import presenters.MessagePresenter;
import use_cases.ConversationManager;
import use_cases.UserManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.UUID;

public class ArchiveConversationAction extends MessageAction {
    private UUID conID;

    public ArchiveConversationAction(UUID userID, UserManager um, ConversationManager cm, UUID conID) {
        super(userID, um, cm);
        this.conID = conID;
    }

    public void run() {
        cm.setArchivedMessage(conID, userID);
    }

    /*
    public void run() {
        this.mp = new MessagePresenter(userID, um, cm);
        ArrayList<UUID> convos = cm.getUserConversationsNotArchived(userID);

        if (convos.isEmpty()) {
            System.out.println("No Conversation to Archive");
            return;
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(mp.promptMainScreenCustom(convos, "Archiveable Conversations"));

        String input;
        try {
            do {
                System.out.println("Select a valid conversation to Archive or type 'exit' to go back to previous menu");
                input = br.readLine();

                if (input.matches("^[0-9]$")) {
                    int idx = Integer.parseInt(input);
                    if (0 < idx && idx < convos.size() + 1) {
                        cm.setArchivedMessage(convos.get(idx - 1), userID);
                        System.out.println(cm.getName(convos.get(idx-1)) + " has been Archived. Now Exiting.");
                        input = "exit";
                    }
                } else if (input.equals("exit")) {
                    System.out.println("Exiting");
                } else {
                    System.out.println("Invalid Input");
                }
            } while (!input.equals("exit"));
        } catch (IOException e) {
            System.out.println("Failed to read input.");
        }
    }
    */

    public String getName() {
        return "Archive A Conversation";
    }
}
