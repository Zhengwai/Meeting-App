import login_system.LoginController;
import users.User;
import users.UserGateway;
import users.UserManager;

public class Main {
    public static void main(String[] args) throws Exception {
        UserGateway ug = new UserGateway();
        UserManager um = new UserManager();
        boolean test = um.addUser(new User("test", "test"));
        boolean test2 = um.addUser(new User("test2", "test2"));
        ug.serializeUserManager("user-manager.ser", um);
        LoginController lc = new LoginController();
        lc.instantiatingMethod();
    }
}
