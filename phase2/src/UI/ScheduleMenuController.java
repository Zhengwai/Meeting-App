package UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ScheduleMenuController extends GeneralController implements Initializable {
    @FXML
    private ComboBox<String> roomFilterComboBox;
    private ObservableList<String> roomList = FXCollections.observableArrayList("room1", "room2", "room3");
    @FXML
    private ComboBox<String> eventTypeFilterComboBox;

    private ObservableList<String>  eventTypesList = FXCollections.observableArrayList("a","b","c");

    @FXML
    private DatePicker datePicker;

    private String fxmlName = "ScheduleMenu";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        eventTypeFilterComboBox.setItems(eventTypesList);
        roomFilterComboBox.setItems(roomList);

    }
}
