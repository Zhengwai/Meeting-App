package controllers.actions;

import controllers.actions.conversationActions.ViewConversationAction;
import entities.Conversation;
import presenters.MessagePresenter;
import use_cases.ConversationManager;
import use_cases.UserManager;

import javax.swing.text.View;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.UUID;

public class GetConversationsAction extends MessageAction {

    public GetConversationsAction(UUID userID, UserManager um, ConversationManager cm) {
        super(userID, um, cm);
    }

    public String getName() {
        return "Conversations";
    }

    @Override
    public void run() /*throws IOException*/ {
        this.mp = new MessagePresenter(userID, um, cm);
        ArrayList<UUID> convos = cm.getUserConversationsNotArchived(userID);

        if (convos.isEmpty()) {
            System.out.println("No Conversations Found. Either add a friend or check your Archived Conversations.");
            return;
        } else {
            System.out.println(mp.promptMainScreen(convos));
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        try {
            do {
                System.out.println("Select a conversation to view or 'exit' to go back.");
                input = br.readLine();

                if (input.matches("^[0-9]*$")) {
                    int idx = Integer.parseInt(input);

                    if (0 < idx && idx < convos.size() + 1) {
                        ViewConversationAction vca = new ViewConversationAction(userID, um, cm, convos.get(idx - 1));
                        vca.run();
                        input = "exit";
                    }

                } else if (!input.equals("exit")){
                    System.out.println("Invalid input!");
                } else {
                    System.out.println("Exiting");
                }

            } while (!input.equals("exit"));
        } catch (NumberFormatException e){
            System.out.println("Not a valid number.");
        } catch (IOException e) {
            System.out.println("Failed to read input.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
