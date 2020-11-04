package message_system;

import users.User;

import java.util.Date;
import java.util.UUID;

public class Reply extends Message {
    private UUID receiver;

    public Reply(UUID senderID, String body, UUID receivingMsg) {
        super(senderID, body);
        this.receiver = receivingMsg;
    }
}
