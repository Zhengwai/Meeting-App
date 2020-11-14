package message_system;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ConversationManager implements Serializable {
    private Map<UUID, Conversation> allConversations;

    public ConversationManager() {
        this.allConversations = new HashMap<>();
    }

    public UUID newConversation() {
        UUID conID = UUID.randomUUID();
        Conversation c = new Conversation();
        this.allConversations.put(conID, c);
        return conID;
    }

    public void deleteConversation(UUID conID) {
        this.allConversations.remove(conID);
    }

    public Conversation getConversation(UUID conID) {
        return allConversations.get(conID);
    }
}
