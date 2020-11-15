package message_system;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ConversationManager implements Serializable {
    private Map<UUID, Conversation> allConversations;
    private UUID id;

    public ConversationManager(UUID userID) {
        this.allConversations = new HashMap<>();
        this.id = userID;
    }

    public UUID newConversation() {
        UUID conID = UUID.randomUUID();
        Conversation c = new Conversation();
        this.allConversations.put(conID, c);
        this.allConversations.get(conID).addMember(id);
        return conID;
    }

    public void addConversation(UUID conID, Conversation c) {
        this.allConversations.put(conID, c);
        this.allConversations.get(conID).addMember(id);
    }

    public void deleteConversation(UUID conID) {
        this.allConversations.get(conID).removeMember(id);
        this.allConversations.remove(conID);
    }

    public UUID[] getAllConversations() {
        UUID[] out = new UUID[allConversations.size()];
        i = 0;
        for (UUID convoID : allConversations.keySet()) {
            UUID[i] = convoID;
            i++;
        }
        return out;
    }

    public Conversation getConversation(UUID conID) {
        return allConversations.get(conID);
    }
}
