package users;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserGatewayTest {

    @Test
    public void testSaveAndReadToFile() {
        UserGateway ug = new UserGateway();
        UserManager um = new UserManager();
        User user1 = new User("testing", "123");
        boolean b = um.addUser(user1);

        if (b) {

            // Saving
            try {
                ug.saveToFile("test-um", um);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Reading
            try {
                UserManager umModified = ug.readFromFile("test-um");
                User user2 = umModified.getUserByID(user1.getID());
                assert (user1.getUsername().equals(user2.getUsername()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Something went wrong.");
        }
    }
}