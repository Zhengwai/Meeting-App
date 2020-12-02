package UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class
OrganizerCreateAccountController extends GeneralController{
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField confirmPasswordTextField;
    @FXML
    private Label usernameErrorLabel;
    @FXML
    private Label passwordErrorLabel;
    @FXML
    private Label registrationSuccessLabel;
    @FXML
    ComboBox<String> selectTypeAccountComboBox;
    ObservableList<String> accountTypeList = FXCollections.observableArrayList("Attendee", "Organizer", "Speaker");
    @FXML
    private void initialize(){
        selectTypeAccountComboBox.setItems(accountTypeList);
    }

    public void createAccountButtonOnAction(ActionEvent event){
        if (checkValidInput()){
            String un = usernameTextField.getText();
            String pw = passwordTextField.getText();
            switch (selectTypeAccountComboBox.getValue()) {
                case "Attendee":
                    mainModel.getUm().registerAttendee(un, pw);
                    break;
                case "Speaker":
                    mainModel.getUm().createSpeaker(un, pw);
                    break;
                case "Organizer":
                    mainModel.getUm().createOrganizer(un, pw);
                    break;
            }
            passwordErrorLabel.setText("");
            usernameErrorLabel.setText("");
            registrationSuccessLabel.setText("Registration success!\n" +
                    "Your username is: " + un +"\n" +
                    "Your password is: " + pw);

        }
    }

    private boolean checkValidInput() {
        registrationSuccessLabel.setText("");
        passwordErrorLabel.setText("");
        usernameErrorLabel.setText("");
        return checkValidUsername() && checkValidPassword();
    }

    private boolean checkValidUsername() {
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

    private boolean checkValidPassword() {
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



