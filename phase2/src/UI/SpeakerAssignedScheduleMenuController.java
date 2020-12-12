package UI;

import entities.Event;
import entities.Speaker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.concurrent.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.UUID;

public class SpeakerAssignedScheduleMenuController extends ScheduleMenuController implements Initializable {
    @FXML
    Button requestCancellationButton;
    @FXML
    Label cancellationPrompt;
    @FXML
    TableView<Event> eventTable;
    @FXML
    TableColumn dateColumn;
    @FXML
    TableColumn startTimeColumn;
    @FXML
    TableColumn endTimeColumn;
    @FXML
    TableColumn nameColumn;
    @FXML
    TableColumn capacityColumn;
    @FXML
    TableColumn statusColumn;
    @FXML
    TableColumn typeColumn;
    @FXML
    TableColumn vipColumn;
    @FXML
    ComboBox eventTypeFilterComboBox;
    @FXML
    ComboBox statusFilterComboBox;
    @FXML
    TextField descriptionBox;
    @FXML
    DatePicker datePicker;

    private ObservableList<Event> allEvents = FXCollections.observableArrayList();

    public SpeakerAssignedScheduleMenuController() throws ClassNotFoundException {

    }


    public void requestCancellationButtonOnAction(ActionEvent event) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void finished(ActionEvent actionEvent) {

    }
}
