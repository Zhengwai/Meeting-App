package login_system;

/**
 * Allows user to login and interact with the system.
 */
public class LoginPresenter {

    LoginController l = new LoginController();

    public LoginPresenter() throws ClassNotFoundException {
    }

    /**
     * Sends a welcome message and allows the user to login to the system.
     */
    /*public void loginToSystem() throws Exception {
        System.out.println("Welcome to the system! :)");
        String email = l.promptEmail();
        String password = l.promptPassword();
        l.login(email, password);
    }*/


}
