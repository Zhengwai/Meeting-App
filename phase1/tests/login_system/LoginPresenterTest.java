package login_system;

import gateway.Serialization;
import users.*;

import java.util.ArrayList;

public class LoginPresenterTest {
    LoginController l = new LoginController();
    Serialization s = new Serialization();

    private ArrayList<User> lst = s.deserializeToArrLstOfUser("/phase1/userManager.ser");

    public void testLogin(){
        l.login(l.promptEmail(),l.promptPassword(),lst);
    }





}
