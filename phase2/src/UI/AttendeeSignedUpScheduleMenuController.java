package UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class AttendeeSignedUpScheduleMenuController extends ScheduleMenuController{

    @FXML
    Button cancelButton;
    @FXML
    Label cancelPrompt;
    
    private String fxmlName = "AttendeeSignedUpScheduleMenu.fxml";
    public AttendeeSignedUpScheduleMenuController() throws ClassNotFoundException {
    }


    public void cancelButtonOnAction(ActionEvent actionEvent) {
    }
}
