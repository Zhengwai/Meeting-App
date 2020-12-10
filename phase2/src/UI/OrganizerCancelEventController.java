package UI;

import entities.Event;
import entities.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class OrganizerCancelEventController extends MenuController{
    @FXML
    Button cancelEventButton;
    @FXML
    TextField eventNameTextField;
    @FXML
    Label eventNameErrorLabel;
    @FXML
    Label cancelEventSuccessLabel;



    public OrganizerCancelEventController() throws ClassNotFoundException {
    }
    public void cancelEventButtonOnAction(ActionEvent actionEvent){
        OrganizerCancelEventAlertBox.display();
        String name = eventNameTextField.getText();
        if (checkValidName()){
            mainModel.getEm().cancelEvent(name);
            cancelEventSuccessLabel.setText("Event successfully cancelled!");
        }
    }
    public boolean checkValidName() {
        eventNameErrorLabel.setText("");
        cancelEventSuccessLabel.setText("");
        if (!mainModel.getEm().hasEvent(eventNameTextField.getText())) {
            eventNameErrorLabel.setText("There's no event with name " + eventNameTextField.getText());
            return false;
        }
        else{
            return true;
        }
    }
}
