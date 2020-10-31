package MessageSystem;

import java.util.Date;

import Users.User;

public class Message {
    private User sender;
    private String text;
    private Date timeSent;

    public Message(User sender, String text, Date timeSent) {
        this.sender = sender;
        this.text = text;
        this.timeSent = timeSent;
    }
}
