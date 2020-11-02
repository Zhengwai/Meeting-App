package message_system;

import users.User;

import org.junit.BeforeClass;
import org.junit.Test;
import users.User;

import javax.accessibility.AccessibleText;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MessageManagerTest {
    private static LinkedList<User> allUsers = new LinkedList<>();

    @BeforeClass
    public static void setUpBeforeClass() {
        User user1 = new User("Billy", "Bob", "billybob123@email.com", "test");
        User user2 = new User("Sam", "Sung", "samsung@email.com", "verypunny");
        User user3 = new User("Final", "User", "finaluser@email.com", "finaltest");
        allUsers.addAll(Arrays.asList(user1, user2, user3));
    }

    @Test(timeout = 50)
    public void testNewMessageManager() {
        UUID id = UUID.fromString("0000-00-00-00-000000");
        new MessageManager(id);
    }

    @Test(timeout = 50)
    public void testGetMessageID() {
        UUID id1 = UUID.fromString("0000-00-00-00-000000");
        MessageManager manager = new MessageManager(id1);
        assertEquals(id1, manager.getUUID());
    }

    @Test(timeout = 50)
    public void testGetAndSendMessage() {
        UUID id1 = UUID.fromString("0000-00-00-00-000000");
        Message message1 = new Message(allUsers.get(0), "Hello!", Calendar.getInstance().getTime());
        Message message2 = new Message(allUsers.get(1), "Hi :)", Calendar.getInstance().getTime());
        MessageManager manager = new MessageManager(id1);
        manager.sendMessage(message1);
        manager.sendMessage(message2);
        assertEquals(manager.getMessages(), Arrays.asList(message1, message2));
    }
}