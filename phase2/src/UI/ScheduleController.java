package UI;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

public class ScheduleController extends GeneralController{
    @FXML
    protected ComboBox<String> roomFilterComboBox;

    @FXML
    protected ComboBox<String> eventTypeFilterComboBox;

    @FXML
    protected DatePicker datePicker;
}
