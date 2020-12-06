package UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class SpeakerAssignedScheduleMenuController extends ScheduleMenuController{
    @FXML
    Button requestCancellationButton;
    @FXML
    Label cancellationPrompt;
    public SpeakerAssignedScheduleMenuController() throws ClassNotFoundException {
    }


    public void requestCancellationButtonOnAction(ActionEvent event) {
    }
}
