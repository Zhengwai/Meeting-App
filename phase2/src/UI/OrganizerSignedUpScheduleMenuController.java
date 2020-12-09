package UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class OrganizerSignedUpScheduleMenuController extends ScheduleMenuController{


    @FXML
    Button cancelButton;
    @FXML
    Label cancelPrompt;

    private String fxmlName = "OrganizerSignedUpScheduleMenu.fxml";

    public OrganizerSignedUpScheduleMenuController() throws ClassNotFoundException {
    }


    public void cancelButtonOnAction(ActionEvent actionEvent) {
    }
}
