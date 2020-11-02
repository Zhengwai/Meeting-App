package message_system;

import users.User;

import java.util.Date;

public class Reply extends Message {
    private User receiver;

    public Reply(User sender, String text, Date timeSent, User receiver) {
        super(sender, text, timeSent);
        this.receiver = receiver;
    }
}
