package UI;

import entities.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.UUID;

public class AttendeeSignedUpScheduleMenuController extends ScheduleMenuControllerA{
    @FXML
    private Button cancelButton;
    private String fxmlName = "AttendeeScheduleMenu.fxml";

    public AttendeeSignedUpScheduleMenuController() throws ClassNotFoundException {
    }

    public void cancelButtonOnAction(ActionEvent event) {

    }
}
