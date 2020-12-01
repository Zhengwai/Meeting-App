package gateways;

import entities.User;
import java.util.ArrayList;

public interface UserDataGateway {
    ArrayList<User> fetchAllUsers();
    void saveAllUsers(ArrayList<User> users);
    void insertNewUser(User u);
}
