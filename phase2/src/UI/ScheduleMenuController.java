package UI;

import entities.Event;
import entities.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ScheduleMenuController extends GeneralController{
    @FXML
    protected ComboBox<String> eventTypeFilterComboBox;
    @FXML
    protected ComboBox<String> statusFilterComboBox;
    @FXML
    protected DatePicker datePicker;
    @FXML
    protected TextField eventNameTextBox;
    @FXML
    protected TableView<Event> eventTable;
    @FXML
    protected TableColumn<Event, String> dateColumn;
    @FXML
    protected TableColumn<Event, String> startTimeColumn;
    @FXML
    protected TableColumn<Event, String> endTimeColumn;
    @FXML
    protected TableColumn<Event, String> nameColumn;
    @FXML
    protected TableColumn<Event, String> capacityColumn;
    @FXML
    protected TableColumn<Event, String> statusColumn;
    @FXML
    protected TableColumn<Event, String> typeColumn;
    @FXML
    protected TextArea descriptionBox;
    @FXML
    protected Button signUpButton;
    @FXML
    protected Label signUpPrompt;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private ObservableList<Event> allEvents = FXCollections.observableArrayList();


    private String fxmlName = "ScheduleMenu";

    public ScheduleMenuController(){
        ArrayList<Event> emEvents = mainModel.getEm().getEvents();
        allEvents.addAll(emEvents);
    }

    @FXML
    public void initialize() {

        eventTypeFilterComboBox.getItems().setAll("", "ted", "vipted", "seminar");
        statusFilterComboBox.getItems().setAll("", "full", "available", "past");

        //Initialize the columns
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().getDateString());
        startTimeColumn.setCellValueFactory(cellData -> cellData.getValue().getStartTimeString());
        endTimeColumn.setCellValueFactory(cellData -> cellData.getValue().getEndTimeString());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
        capacityColumn.setCellValueFactory(cellData -> cellData.getValue().getCapacityString());
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().getStatus());

        FilteredList<Event> filteredEvents = new FilteredList<>(allEvents, p -> true);


        eventTypeFilterComboBox.selectionModelProperty().addListener((observable, oldValue, newValue) -> {
            filteredEvents.setPredicate(event -> {
                // If filter text is empty, display all persons.
                String type1 = newValue.toString();
                if (newValue.isEmpty() || type1.equals("")) {
                    return true;
                }
                return event.getType().toString().equals(type1);
            });
        });

        statusFilterComboBox.selectionModelProperty().addListener((observable, oldValue, newValue) -> {
            filteredEvents.setPredicate(event -> {
                // If filter text is empty, display all persons.
                String type1 = newValue.toString();
                if (newValue.isEmpty() || type1.equals("")) {
                    return true;
                }
                return event.getStatus().toString().equals(type1);
            });
        });

        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            filteredEvents.setPredicate(event -> {
                // If filter text is empty, display all persons.
                String type1 = newValue.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                return event.getDateString().toString().equals(type1);
            });
        });

    }
}
