package entities;

import entities.Message;

import java.util.UUID;

//TODO: Reply should be present in Test Cases (don't create a file, just create an instance in the ConversationTest)

/**
 * Entity class.
 * A message that's replying to another message.
 */
public class Reply extends Message {
    private UUID receiver;

    /**
     * @param senderID The UUID of the user sending this reply.
     * @param body The text content of this reply.
     * @param receivingMsg The id of the message this reply is for.
     */
    public Reply(UUID senderID, String body, UUID receivingMsg) {
        super(senderID, body);
        this.receiver = receivingMsg;
    }
}
