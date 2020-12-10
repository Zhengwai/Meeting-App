package UI;

import entities.Event;
import entities.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    TextField eventRoomTextField;
    @FXML
    Label eventRoomErrorLabel;
    @FXML
    Label createEventSuccessLabel;
    @FXML
    ComboBox<String> selectTypeEventComboBox;
    @FXML
    Label hasTypeLabel;
    @FXML
    public void initialize(){
        selectTypeEventComboBox.getItems().removeAll(selectTypeEventComboBox.getItems());
        selectTypeEventComboBox.getItems().addAll("","TED","VIP","SEMINAR");
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

   public void createEventButtonOnAction(ActionEvent actionEvent) {
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
       String name = eventNameTextField.getText();
       int capacity = Integer.parseInt(eventCapacityTextField.getText());
       LocalDateTime stime = LocalDateTime.parse(eventStartTimeTextField.getText(),formatter);
       LocalDateTime etime = LocalDateTime.parse(eventEndTimeTextField.getText(),formatter);
       //UUID roomid = UUID.fromString(eventRoomTextField.getText());
       //Room room = mainModel.getEm().getRoomByID(roomid);
       String roomName = eventRoomTextField.getText();
       Room room = new Room(capacity, roomName);
       Event tempE = mainModel.getEm().createTempEvent(name, capacity,stime,etime);

        if (checkValidInput(room, tempE) && hasChosenType()){
            mainModel.getEm().createAndAddEvent(name, capacity, stime, etime, room.getID(), selectTypeEventComboBox.getValue());
            mainModel.getEm().addRoom(room);
            createEventSuccessLabel.setText("Event created success!");
        }
    }

    protected boolean checkValidInput(Room room, Event tempE){
        createEventSuccessLabel.setText("");
        eventRoomErrorLabel.setText("");
        return checkValidRoom(room, tempE);

    }

    protected boolean checkValidRoom(Room room, Event tempE){
        if (!mainModel.getEm().roomAvailableForEvent(room, tempE)){
            eventRoomErrorLabel.setText("The room selected is taken at this time period!");
            return false;
        }else{
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


}
