package controllers.actions.conversationActions;

import use_cases.ConversationManager;
import use_cases.UserManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.UUID;

public class RemoveMemberAction extends ConversationAction {
    public RemoveMemberAction(UUID userID, UserManager um, ConversationManager cm, UUID convID) {
        super(userID, um, cm, convID);
    }

    public void run() {
        ArrayList<UUID> members = cm.getMemberIDsInConversation(convID);

        /*
        if (members.size() == 1) {
            System.out.println("No Members to Remove");
            return;
        }
        */

        System.out.println(mp.promptMembers(convID));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;

        try {
            do {
                System.out.println("Enter corresponding number to user you want to remove, or 'exit' to go back. You can remove yourself as well.");
                input = br.readLine();

                if (input.matches("^[0-9]$")) {
                    int idx = Integer.parseInt(input);

                    if (0 < idx && idx < members.size() + 1) {
                        cm.removeMember(convID, members.get(idx - 1));
                        input = "exit";
                    }

                } else if (!input.equals("exit")){
                    System.out.println("Invalid input!");
                } else {
                    System.out.println("Exiting");
                }

            } while(!input.equals("exit"));
        } catch (IOException e) {
            System.out.println("Failed to read input.");
        }



    }

    public String getName() {
        return "Remove a member";
    }
}
