package controllers.actions;

import use_cases.ConversationManager;
import use_cases.UserManager;

import java.io.IOException;
import java.util.UUID;

public class MessageAllSpeakersAction extends MessageAllAction {

    public MessageAllSpeakersAction(UUID userID, UserManager um, ConversationManager cm) {super(userID, um, cm);}

    public void run() throws IOException {
        handleMessageAll(this.um.getAllSpeakersUser());
    }

    /**
     * Helper method for this class's <code>run()</code> method.
     * Handles messaging all speakers.
     */

    public String getName() {
        return "Message All Speakers";
    }
}
