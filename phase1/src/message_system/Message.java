package message_system;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Entity class.
 * Stores all relevant data pertaining to a Message.
 */
public class Message implements Serializable {
    private final UUID messageID;
    private final UUID senderID;
    private String body;
    private Date timeSent;

    /**
     * Encapsulates all data into a Message class.
     * @param senderID The UUID of the user who sent this message
     * @param body The text content of the message
     */
    public Message( UUID senderID, String body) {
        this.messageID = UUID.randomUUID();
        this.senderID = senderID;
        this.body = body;
        this.timeSent = Calendar.getInstance().getTime();
    }

    /**
     * Getter
     * @return the UUID of this message
     */
    public UUID getMessageID() {
        return this.messageID;
    }

    /**
     * Getter
     * @return the UUID of the person who sent this message
     */
    public UUID getSenderID() {
        return this.senderID;
    }

    /**
     * Getter
     * @return the text content of this message.
     */
    public String getBody() {
        return this.body;
    }

    /**
     * Getter
     * @return the date of when this message was sent.
     */
    public Date getTimeSent() {
        return this.timeSent;
    }
}
