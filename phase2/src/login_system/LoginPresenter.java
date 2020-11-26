package login_system;

/**
 * Allows user to login and interact with the system.
 */
public class LoginPresenter {

    LoginController l = new LoginController();

    public LoginPresenter() throws ClassNotFoundException {
    }

    public void usernameTaken(){
        System.out.println("Sorry, the username you have chosen is already taken. \n" +
                "Would you like to retry? Enter 1 for yes, 0 for no.");
    }

    public void loginPrompt(){
        System.out.println("Please enter 1 to login, 2 to create a new account, or 0 to exit the program");
    }

    public void promptUser(){
        System.out.println("Please enter your username: ");
    }

    public void promptPassword(){
        System.out.println("Please enter your password: ");
    }

    public void successfulLogin(){
        System.out.println("Login success!");
    }

    public void incorrectUserPass(){
        System.out.println("Incorrect username or password, please try again");
    }

    public void chooseUser(){
        System.out.println("Please choose a username: ");
    }

    public void choosePass(){
        System.out.println("Please choose a username: ");
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
