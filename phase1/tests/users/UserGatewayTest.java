package users;

import entities.User;
import gateways.UserGateway;
import org.testng.annotations.Test;
//import org.testing.annotations.Test;
import use_cases.UserManager;

import java.io.IOException;

import static org.junit.Assert.*;


public class UserGatewayTest {

    @Test
    public void testSaveAndReadToFile() throws IOException, ClassNotFoundException {
        UserGateway ug = new UserGateway();
        UserManager um = new UserManager();
        User user1 = new User("testing", "123");
        boolean b = um.addUser(user1);

        if (b) {

            // Saving
            try {
                ug.serializeUserManager("test-um", um);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Reading
            try {
                UserManager umModified = ug.deserializeUserManager("test-um");
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
