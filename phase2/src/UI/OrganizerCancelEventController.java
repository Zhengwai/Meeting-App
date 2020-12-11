package UI;

import entities.Event;
import entities.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class OrganizerCancelEventController extends MenuController{
    @FXML
    Button cancelEventButton;
    @FXML
    ComboBox<String> eventNameComboBox;
    @FXML
    Label eventNameErrorLabel;
    @FXML
    Label cancelEventSuccessLabel;



    public OrganizerCancelEventController() throws ClassNotFoundException {

    }
    public void initialize(){
        ArrayList<String> eventNames = mainModel.getEm().getAllEventNames();
        System.out.println(eventNames);
        eventNameComboBox.getItems().removeAll();
        eventNameComboBox.getItems().addAll(eventNames);
    }
    public void cancelEventButtonOnAction(ActionEvent actionEvent){
        if (hasChosenEvent()){
            String name = eventNameComboBox.getValue();
            mainModel.getEm().cancelEvent(name);
            cancelEventSuccessLabel.setText("Event successfully cancelled!");
        }
    }
    public boolean hasChosenEvent() {
        eventNameErrorLabel.setText("");
        if (eventNameComboBox.getSelectionModel().isEmpty()) {
            eventNameErrorLabel.setText("Please select an event!");
            return false;
        } else {
            return true;
        }
    }
}
