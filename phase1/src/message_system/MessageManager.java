package message_system;

import java.util.LinkedList;
import java.util.UUID;

public class MessageManager {
    //TODO: Implement Serializable

    private LinkedList<Message> messages;
    private final UUID managerID;

    public MessageManager(UUID id) {
        this.messages = new LinkedList<>();
        this.managerID = id;
    }

    public UUID getUUID() {
        return this.managerID;
    }

    public void sendMessage(Message message) {
        messages.add(message);
    }

    public LinkedList<Message> getMessages() {
        return messages;
    }
}
