package controllers.actions;

import entities.Conversation;
import presenters.MessagePresenter;
import use_cases.ConversationManager;
import use_cases.UserManager;

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
    public void run() throws IOException {
        this.mp = new MessagePresenter(userID, um, cm);
        ArrayList<Conversation> convos = cm.getUserConversations(userID);

        if (convos.isEmpty()) {
            System.out.println("Add a friend to start a conversation!");
            return;
        } else {
            System.out.println(mp.promptMainScreen(convos));
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        try {
            int index = Integer.parseInt(input);
            if (0 <= index && index < convos.size()) {
                String conInput;
                while (true) {
                    Conversation c = convos.get(index);
                    System.out.println(mp.promptConversationScreen(c.getID()));

                    if (c.hasOwner()) {
                        if (c.getReadOnly() && !(c.getOwner().equals(userID))) {
                            System.out.println("Type exit to leave");
                            conInput = br.readLine();
                            if (conInput.equals("exit")) {
                                break;
                            }
                        } else {
                            System.out.println("Enter your message or type 'exit' to leave.");
                            conInput = br.readLine();

                            if (conInput.equals("exit")) {
                                break;
                            }

                            cm.sendMessageInConversation(c.getID(), userID, conInput);
                        }
                    } else {
                        System.out.println("Enter your message or type 'exit' to leave.");
                        conInput = br.readLine();

                        if (conInput.equals("exit")) {
                            break;
                        }

                        cm.sendMessageInConversation(c.getID(), userID, conInput);
                    }
                }
            } else {
                System.out.println("There is no conversation labelled with that number.");
            }
        } catch (NumberFormatException e){
            System.out.println("Not a valid number.");
        } catch (IOException e) {
            System.out.println("Failed to read input.");
        }
    }
}
