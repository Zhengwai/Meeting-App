package Repository;

import entities.User;

public interface UserData {
    public void addUser(User newUser);
    public boolean loginVerification(String username, String password);
}
