package controllers.actions;

import use_cases.ConversationManager;
import use_cases.UserManager;

import java.io.IOException;
import java.util.UUID;

public class MessageAllSpeakersAction extends MessageAllAction {
    String body;
    String title;


    public MessageAllSpeakersAction(UUID userID, UserManager um, ConversationManager cm, String body, String title) {
        super(userID, um, cm);
        this.body = body;
        this.title = title;
    }

    public void run() {
        handleMessage(this.um.getAllSpeakersUser(), body, title);
    }

    /**
     * Helper method for this class's <code>run()</code> method.
     * Handles messaging all speakers.
     */

    public String getName() {
        return "Message All Speakers";
    }
}
