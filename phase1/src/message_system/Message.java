package message_system;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Message {
    private final UUID messageID;
    private final UUID senderID;
    private String body;
    private Date timeSent;

    public Message( UUID senderID, String body) {
        this.messageID = UUID.randomUUID();
        this.senderID = senderID;
        this.body = body;
        this.timeSent = Calendar.getInstance().getTime();
    }

    public UUID getConversationID() {
        return this.messageID;
    }

    public UUID getSenderID() {
        return this.senderID;
    }

    public String getBody() {
        return this.body;
    }

    public Date getTimeSent() {
        return this.timeSent;
    }
}
