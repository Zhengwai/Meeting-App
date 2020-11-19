package message_system;

import users.User;
import users.UserManager;


import java.io.IOException;
import java.util.UUID;


public class MessagePresenterTest {

    @org.junit.Test
    public void testPromptMainScreen() throws ClassNotFoundException {
        // SETUP
        UserManager um = new UserManager();
        User user1 = new User("johndoe", "abcde");
        User user2 = new User("billybob", "12345");
        User user3 = new User("guyfieri", "password");
        ConversationManager cm = new ConversationManager();
        UUID conID1 = cm.newConversation();
        UUID conID2 = cm.newConversation();
        MessagePresenter mp = new MessagePresenter(user1, um);

        try {
            um.addUser(user1);
            um.addUser(user2);
            um.addUser(user3);
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }

        cm.getConversation(conID1).addMember(user1.getID());
        user1.addConversation(conID1);
        cm.getConversation(conID1).addMember(user2.getID());
        user2.addConversation(conID1);

        cm.getConversation(conID2).addMember(user1.getID());
        user1.addConversation(conID2);
        cm.getConversation(conID2).addMember(user3.getID());
        user3.addConversation(conID2);


        // ACTUAL TEST
        String output = mp.promptMainScreen(cm.getConversations(user1.getConversations()));
        assert (output.equals("Contacts: \n(0) billybob\n(1) guyfieri\n"));
    }

    @org.junit.Test
    public void testPromptConversationScreen() throws ClassNotFoundException, IOException {
        UserManager um = new UserManager();
        User user1 = new User("johndoe", "abcde");
        User user2 = new User("billybob", "12345");

        um.addUser(user1);
        um.addUser(user2);

        ConversationManager cm = new ConversationManager();
        UUID conID = cm.newConversation();
        MessagePresenter mp = new MessagePresenter(user1, um);

        user1.addConversation(conID);
        user2.addConversation(conID);

        Conversation c = cm.getConversation(conID);
        Message msg1 = new Message(user1.getID(), "Yo");
        Message msg2 = new Message(user2.getID(), "What's popping?");
        Message msg3 = new Message(user1.getID(), "Brand new whip just hopped in");
        c.sendMessage(msg1);
        c.sendMessage(msg2);
        c.sendMessage(msg3);

        String output = mp.promptConversationScreen(c);
        System.out.println(output);
    }

}