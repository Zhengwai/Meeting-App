package UI;

import entities.Event;
import entities.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrganizerCreateEventController extends MenuController{
    @FXML
    Button createEventButton;
    @FXML
    TextField eventNameTextField;
    @FXML
    TextField eventStartTimeTextField;
    @FXML
    TextField eventEndTimeTextField;
    @FXML
    TextField eventCapacityTextField;
    @FXML
    TextArea descriptionTextArea;
    @FXML
    Label hasRoomLabel;
    @FXML
    Label createEventSuccessLabel;
    @FXML
    ComboBox<String> selectTypeEventComboBox;
    @FXML
    ComboBox<String> selectRoomComboBox;
    @FXML
    Label hasTypeLabel;
    @FXML
    CheckBox vipStatus;

    public void initialize(){
        selectTypeEventComboBox.getItems().removeAll(selectTypeEventComboBox.getItems());
        selectTypeEventComboBox.getItems().addAll("","TED","SEMINAR", "PARTY");
        ArrayList<String> roomNames = mainModel.getEm().getAllRoomNames();
        selectRoomComboBox.getItems().removeAll();
        selectRoomComboBox.getItems().addAll(roomNames);
    }

    //private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm");
    //private String name = eventNameTextField.getText();
    //private int capacity = Integer.parseInt(eventCapacityTextField.getText());
    //private LocalDateTime stime = LocalDateTime.parse(eventStartTimeTextField.getText());
    //private LocalDateTime etime = LocalDateTime.parse(eventEndTimeTextField.getText());
    //private UUID roomid = UUID.fromString(eventRoomTextField.getText());
    //private Room room = mainModel.getEm().getRoomByID(roomid);
    //private Event tempE = mainModel.getEm().createTempEvent(name, capacity,stime,etime);

    public OrganizerCreateEventController() throws ClassNotFoundException {
    }

   public void createEventButtonOnAction(ActionEvent actionEvent) throws IOException {
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
       String name = eventNameTextField.getText();
       int capacity = Integer.parseInt(eventCapacityTextField.getText());
       LocalDateTime stime = LocalDateTime.parse(eventStartTimeTextField.getText(),formatter);
       LocalDateTime etime = LocalDateTime.parse(eventEndTimeTextField.getText(),formatter);
       //UUID roomid = UUID.fromString(eventRoomTextField.getText());
       //Room room = mainModel.getEm().getRoomByID(roomid);
       String roomName = selectRoomComboBox.getValue();
       Room room = mainModel.getEm().getRoomByName(roomName);
       Boolean vip = vipStatus.isSelected();

       Event tempE = mainModel.getEm().createTempEvent(name, capacity,stime,etime);

        if (checkValidInput(room, tempE)){
            String type = selectTypeEventComboBox.getValue();
            mainModel.getEm().createAndAddEvent(name, capacity, stime, etime, room.getRoomName(), type,descriptionTextArea.getText(), vip);
            createEventSuccessLabel.setText("Event created success!");
        }
    }

    protected boolean checkValidInput(Room room, Event tempE){
        createEventSuccessLabel.setText("");
        hasRoomLabel.setText("");
        return hasChosenRoom()&&hasChosenType()&&roomAvailable(room,tempE);

    }
    protected boolean roomAvailable(Room room, Event tempE){
        if(!roomAvailable(room,tempE)){
            hasRoomLabel.setText("Selected room isn't available for this event.");
            return false;
        }
        else{
            return true;
        }
    }
    protected boolean hasChosenType(){
        hasTypeLabel.setText("");
        if (selectTypeEventComboBox.getSelectionModel().isEmpty()){
            hasTypeLabel.setText("Please select an event type!");
            return false;
        }else{
            return true;
        }
    }

    protected boolean hasChosenRoom(){
        hasRoomLabel.setText("");
        if (selectRoomComboBox.getSelectionModel().isEmpty()){
            hasRoomLabel.setText("Please select a room!");
            return false;
        }else{
            return true;
        }
    }


}
