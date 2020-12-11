package UI;

import entities.Event;
import entities.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import java.util.UUID;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrganizerCreateRoomController extends MenuController{
    @FXML
    TextField roomNameTextField;
    @FXML
    TextField roomCapacityTextField;
    @FXML
    Label RoomNameErrorLabel;
    @FXML
    Label RoomCapacityErrorLabel;
    @FXML
    Label createRoomSuccessLabel;
    @FXML
    Button createRoomButton;
    public OrganizerCreateRoomController() throws ClassNotFoundException {
    }
    public void createRoomButtonOnAction(ActionEvent actionEvent){
        String name = roomNameTextField.getText();
        String capacity = roomCapacityTextField.getText();
        if (checkValidName()&&checkValidCapacity()){
            mainModel.getEm().createAndAddRoom(name,Integer.parseInt(capacity));
            createRoomSuccessLabel.setText("Event successfully cancelled!");
        }
    }
    public boolean checkValidName() {
        RoomNameErrorLabel.setText("");
        createRoomSuccessLabel.setText("");
        if (mainModel.getEm().hasRoom(roomNameTextField.getText())) {
            RoomNameErrorLabel.setText("Room name already taken no. ");
            return false;
        }
        else{
            return true;
        }
    }
    public boolean checkValidCapacity(){
        try {
            int cap = Integer.parseInt(roomCapacityTextField.getText());
            if(cap>0){
                return true;
            }
            else{
                RoomCapacityErrorLabel.setText("Please enter a positive integer for room capacity.");
                return false;
            }
        }
        catch( NumberFormatException e ) {
            RoomCapacityErrorLabel.setText("Please enter a positive integer for room capacity.");
            return false;
        }
    }

}


