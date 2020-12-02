package UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class ScheduleMenuController extends GeneralController{
    @FXML
    protected ComboBox<String> roomFilterComboBox;

    @FXML
    protected ComboBox<String> eventTypeFilterComboBox;

    @FXML
    protected DatePicker datePicker;

}
