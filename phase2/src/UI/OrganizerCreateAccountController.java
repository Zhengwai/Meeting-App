package UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class
OrganizerCreateAccountController extends RegisterController {

    @FXML
    ComboBox<String> selectTypeAccountComboBox;
    @FXML
    Label hasTypeLabel;

    private String fxmlName = "OrganizerCreateAccount.fxml";

    public OrganizerCreateAccountController() throws ClassNotFoundException {
    }

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
            statusLabel.setVisible(true);
            statusLabel.setText("Registration success!\n" +
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
        statusLabel.setVisible(true);
        if (selectTypeAccountComboBox.getSelectionModel().isEmpty()){
            statusLabel.setText("Please select a user type!");
            return false;
        }
        return true;
    }
}



