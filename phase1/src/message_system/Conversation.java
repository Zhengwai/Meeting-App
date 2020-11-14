package message_system;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.UUID;

public class Conversation {
    private ArrayList<UUID> members;
    private ArrayList<Message> messages;

    public Conversation() {
        this.members = new ArrayList<>();
        this.messages = new ArrayList<>();
    }

    public void sendMessage(Message msg) {
        messages.add(msg);
    }

    public Message[] getMessages() {
        Message[] out = new Message[messages.size()];
        out = messages.toArray(out);
        return out;
    }

    // Can handle the spec where Organizer should message all users
    // (i.e create a group chat with everyone in a particular event)
    public void addMember(UUID userID) {
        this.members.add(userID);
    }

    public void removeMember(UUID userID) {
        members.remove(userID);
    }
}
