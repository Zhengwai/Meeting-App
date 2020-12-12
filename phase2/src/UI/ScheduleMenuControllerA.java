package UI;

import entities.Event;
import entities.Room;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;

public class ScheduleMenuControllerA extends GeneralController{
    @FXML
    protected ComboBox<String> eventTypeFilterComboBox;
    @FXML
    protected ComboBox<String> statusFilterComboBox;
    @FXML
    protected ComboBox<String> roomFilterComboBox;
    @FXML
    protected DatePicker datePicker;
    @FXML
    protected TextField eventNameTextBox;
    @FXML
    protected TableView<Event> eventTable = new TableView<>();
    @FXML
    protected TableColumn<Event, String> dateColumn = new TableColumn<>("date");
    @FXML
    protected TableColumn<Event, String> startTimeColumn = new TableColumn<>("Start Time");
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
    protected TableColumn<Event, String> vipColumn;
    @FXML
    protected TextArea descriptionBox;
    @FXML
    protected Button cancelButton;
    @FXML
    protected Label cancelEvent;
    @FXML
    protected Button switchButton;

    private ObservableList<Event> allEvents = FXCollections.observableArrayList();


    private String fxmlName = "ScheduleMenu";
    private String evtSelected="";
    public ScheduleMenuControllerA() throws ClassNotFoundException {
        super();
        allEvents.removeAll();
        ArrayList<Event> evts = mainModel.getEm().getEventsByUser(mainModel.getCurrentUser());
        allEvents.addAll(evts);
    }

    @FXML
    public void initialize(){
        cancelButton.setDisable(true);
        eventTypeFilterComboBox.getItems().setAll("", "ted", "vipted", "seminar");
        statusFilterComboBox.getItems().setAll("", "full", "available", "past");
        //Initialize the columns
        System.out.println(allEvents);
        eventTable.setItems(allEvents);
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().getDateString());
        startTimeColumn.setCellValueFactory(cellData -> cellData.getValue().getStartTimeString());
        endTimeColumn.setCellValueFactory(cellData -> cellData.getValue().getEndTimeString());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
        capacityColumn.setCellValueFactory(cellData -> cellData.getValue().getCapacityString());
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().getStatus());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().getType());
        vipColumn.setCellValueFactory(cellData -> cellData.getValue().getVipString());

        FilteredList<Event> filteredEvents = new FilteredList<>(allEvents, p -> true);

        eventTable.setOnMousePressed(e ->{
            if (e.getClickCount() == 1 && e.isPrimaryButtonDown() ){
                String des = eventTable.getSelectionModel().getSelectedItem().getDescription();
                UUID roomID = eventTable.getSelectionModel().getSelectedItem().getRoom();
                String roomName = mainModel.getEm().getRoomByID(roomID).getRoomName();
                descriptionBox.setText("Room: "+ roomName+"\nDescription of this event: "+des);
                cancelButton.setDisable(false);
                evtSelected = eventTable.getSelectionModel().getSelectedItem().getName().getValue();
            }
        });

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
    public String getEvtSelected(){
        return evtSelected;
    }
}
