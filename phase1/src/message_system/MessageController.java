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
        return sender.msgMgr.getMessages(convoId);
    }


    public void messageConversation(String msg, UUID convoid, User sender) {
        //TODO: Take a String msg. Instantiate a new Message object and append the Message object to msgMgr.messages.
        //TODO: Add UUID senderID and MessageManager msgMgr to User class

        UUID senderId = sender.senderID;
        Message newMsg = Message(senderId, msg);
        sender.msgMgr.sendMessage(convoid, newMsg);

    }

    public void createConversation(User receiver, User sender) {
        //TODO: Add MessageManager msgMgr to User class

        convoId = sender.msgMgr.newConversation();
        receiver.msgMgr.addConversation(convoId);

    }


}
