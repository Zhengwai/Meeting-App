package message_system;

import java.util.UUID;
import users.UserManager;

public class MessageController {
    // Will handle user interaction

    public void run() {

    }


    // The Controller should no
    public Message[] getMessages(UUID convoId, User sender) {
        //TODO: Returns messagess in conversation requested by User
        return sender.convMgr.getConversation(convoId).getMessages();
    }

    public UUID[] getAllConversations(User sender) {
        return sender.convMgr.getAllConversations();
    }

    public void messageConversation(String msg, UUID convoID, User sender) {
        UUID senderID = sender.id;
        Message newMsg = Message(senderID, msg);
        sender.convMgr.getConversation(convoID).sendMessage(newMsg);
    }

    public void createConversation(User receiver, User sender) {
        conID = sender.convMgr.newConversation();
        c = sender.convMgr.getConversation(conID);
        receiver.convMgr.addConversation(conID, c);
    }

    public void addMember(User receiver, User sender, UUID conID) {
        receiver.convMgr.addConversation(conID, sender.convMgr.getConversation(conID))
    }

    public void removeConversation(User sender, UUID conID) {
        sender.convMgr.deleteConversation(conID);
    }

}
