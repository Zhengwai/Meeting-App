package test;

import Users.*;
import MessageSystem.*;
import org.junit.*;

import java.util.Date;

class MessageTest {
    static User user1;

    @BeforeClass
    public static void setUpBeforeClass() {
        user1 = new Attendee("Billy", "Bob", "billybob123@email.com", "password");
    }

    @Test(timeout = 50)
    public void testMessage() {
        new Message(user1, "Hi there", new Date());
    }
}