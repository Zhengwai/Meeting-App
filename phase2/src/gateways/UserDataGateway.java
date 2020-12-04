package gateways;

import entities.User;
import java.util.ArrayList;
import java.util.UUID;

public interface UserDataGateway {
    ArrayList<User> fetchAllUsers();
    void updateUserType(UUID userID, String newType);
    void updateUserPassword(UUID userID, String newPassword);
    void updateUserFriends(UUID userID, ArrayList<UUID> newFriendsList);
    void updateUserEvents(UUID userID, ArrayList<UUID> newEventsList);
    void insertNewUser(User u);
}
