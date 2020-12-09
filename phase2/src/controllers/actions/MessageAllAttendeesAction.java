package controllers.actions;

import use_cases.ConversationManager;
import use_cases.UserManager;

import java.io.IOException;
import java.util.UUID;

public class MessageAllAttendeesAction extends MessageAllAction {
    String msgBody;
    String msgTitle;

    public MessageAllAttendeesAction(UUID userID, UserManager um, ConversationManager cm, String msgBody, String msgTitle) {
        super(userID, um, cm);
        this.msgBody = msgBody;
        this.msgTitle = msgTitle;
    }

    public void run() {
        handleMessage(this.um.getAllAttendees(), msgBody, msgTitle);
    }


    public String getName() {
        return "Message All Attendees";
    }
}
