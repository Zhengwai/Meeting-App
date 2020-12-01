package Repository;

import entities.User;
import use_cases.EventManager;
import use_cases.UserManager;

public interface UserData {
    public UserManager deserializeUserManager(String filePath) throws ClassNotFoundException;
    public void serializeUserManager(String filePath, UserManager um);


}
