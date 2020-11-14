package message_system;

public class MessagePresenter {
    // Will handle what should be printed to console.

    public void printMessage(Message msg) {
        System.out.println(msg.timeSent + " " + msg.senderID + " " + msg.body);
    }

    public void printConversation(Message[] msgs) {
        for (int i = 0; i < msgs.size(); i++) {
            printMessage(msgs[i]);
        }
    }

    public void printConversations(UUID[] convoIDs) {
        for (int i = 0; i < convoIDs.size(); i++) {
            System.out.println(convoIDs[i]);
        }
    }
}
