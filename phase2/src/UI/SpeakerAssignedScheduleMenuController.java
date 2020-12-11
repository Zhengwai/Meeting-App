package UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class SpeakerAssignedScheduleMenuController extends ScheduleMenuController implements Initializable {
    @FXML
    Button requestCancellationButton;
    @FXML
    Label cancellationPrompt;
    public SpeakerAssignedScheduleMenuController() throws ClassNotFoundException {
    }


    public void requestCancellationButtonOnAction(ActionEvent event) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
