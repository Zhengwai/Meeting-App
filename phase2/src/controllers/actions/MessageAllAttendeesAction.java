package controllers.actions;

import use_cases.ConversationManager;
import use_cases.UserManager;

import java.io.IOException;
import java.util.UUID;

public class MessageAllAttendeesAction extends MessageAllAction {


    public MessageAllAttendeesAction(UUID userID, UserManager um, ConversationManager cm) {super(userID, um, cm);}

    public void run() throws IOException {
        handleMessageAll(this.um.getAllAttendees());
    }


    public String getName() {
        return "Message All Attendees";
    }
}
