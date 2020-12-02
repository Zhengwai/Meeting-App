package UI;

import entities.User;
import gateways.LoginGateway;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import use_cases.UserManager;

import javafx.event.ActionEvent;
public class RegisterController extends GeneralController {

    @FXML
    protected Button createAccountButton;
    @FXML
    protected TextField usernameTextField;
    @FXML
    protected TextField passwordTextField;
    @FXML
    protected TextField confirmPasswordTextField;
    @FXML
    protected Label usernameErrorLabel;
    @FXML
    protected Label passwordErrorLabel;
    @FXML
    protected Label registrationSuccessLabel;

    private String fxmlName = "register.fxml";


    public void createAccountButtonOnAction(ActionEvent event){
        if (checkValidInput()){
            String un = usernameTextField.getText();
            String pw = passwordTextField.getText();
            mainModel.getUm().registerAttendee(un, pw);
            passwordErrorLabel.setText("");
            usernameErrorLabel.setText("");
            registrationSuccessLabel.setText("Registration success!\n" +
                                                "Your username is: " + un +"\n" +
                                                "Your password is: " + pw);

        }
}

    protected boolean checkValidInput() {
        registrationSuccessLabel.setText("");
        passwordErrorLabel.setText("");
        usernameErrorLabel.setText("");
        return checkValidUsername() && checkValidPassword();
    }

    protected boolean checkValidUsername() {
        if (usernameTextField.getText().equals("")){
            usernameErrorLabel.setText("Username cannot be empty!");
            return false;
        }
        if (usernameTextField.getText().contains(" ")){
            usernameErrorLabel.setText("Username cannot contain any space!");
            return false;
        }
        String un = usernameTextField.getText();
        if (!mainModel.getUm().isValidUserName(un)){
            usernameErrorLabel.setText("Username is taken, please choose another one!");
            return false;
        }
        usernameErrorLabel.setText("");
        return true;
    }

    protected boolean checkValidPassword() {
        if (!passwordTextField.getText().equals(confirmPasswordTextField.getText())){
            passwordErrorLabel.setText("Inconsistent password, make sure you confirm your password.");
            return false;
        }

        if (passwordTextField.getText().equals("")){
            passwordErrorLabel.setText("Password cannot be empty!");
            return false;
        }
        if (passwordTextField.getText().contains(" ")){
            passwordErrorLabel.setText("Password cannot contain any space!");
            return false;
        }
        passwordErrorLabel.setText("");
        return true;
    }
}
