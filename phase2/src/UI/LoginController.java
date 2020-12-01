package UI;
import gateways.LoginGateway;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import use_cases.UserManager;

import java.awt.*;
import javafx.event.ActionEvent;

public class LoginController {

    LoginGateway lg = new LoginGateway();

    @FXML
    private Button loginButton;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Button registerButton;
    @FXML
    private Label promptLabel;

    public void loginButtonOnAction(ActionEvent event) {
        //Check Username and Password:
        if (lg.verifyLogin(usernameTextField.getText(), passwordTextField.getText())) {
            //TODO: implement what to do after the user is verified to login.
            //TODO: first decide on what type of user it is, them open the associated window.
        } else {
            promptLabel.setText("Username or password incorrect, please try again");
        }
    }

}
