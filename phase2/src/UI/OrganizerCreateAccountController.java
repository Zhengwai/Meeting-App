package UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class
OrganizerCreateAccountController extends RegisterController {

    @FXML
    ComboBox<String> selectTypeAccountComboBox;
    @FXML
    Label hasTypeLabel;

    private String fxmlName = "OrganizerCreateAccount.fxml";

    @FXML
    public void initialize() {
        selectTypeAccountComboBox.getItems().removeAll(selectTypeAccountComboBox.getItems());
        selectTypeAccountComboBox.getItems().addAll("Attendee", "Organizer", "Speaker", "VIP");
    }

    @Override
    public void createAccountButtonOnAction(ActionEvent event) {
        if (checkValidInput() && hasChosenType()) {
            String un = usernameTextField.getText();
            String pw = passwordTextField.getText();
            createBasedOnType(un, pw);
            passwordErrorLabel.setText("");
            usernameErrorLabel.setText("");
            registrationSuccessLabel.setText("Registration success!\n" +
                    "Your username is: " + un + "\n" +
                    "Your password is: " + pw);

        }

    }

    private void createBasedOnType(String un, String pw){
        if (selectTypeAccountComboBox.getValue().equals("Attendee")){
            mainModel.getUm().registerAttendee(un, pw);
        }
        if (selectTypeAccountComboBox.getValue().equals("Organizer")){
            mainModel.getUm().registerOrganizer(un, pw);
        }
        if (selectTypeAccountComboBox.getValue().equals("Speaker")){
            mainModel.getUm().registerSpeaker(un, pw);
        }
        if (selectTypeAccountComboBox.getValue().equals("VIP")){
            mainModel.getUm().registerVIP(un, pw);
        }
    }

    private boolean hasChosenType(){
        hasTypeLabel.setText("");
        if (selectTypeAccountComboBox.getSelectionModel().isEmpty()){
            hasTypeLabel.setText("Please select a user type!");
            return false;
        }
        return true;
    }
}



