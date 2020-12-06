package UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;

public class OrganizerScheduleMenuController extends ScheduleMenuController{

    @FXML
    Button createEventButton;

    @FXML
    Button cancelEventButton;
    private String fxmlName = "OrganizerScheduleMenu.fxml";

    public OrganizerScheduleMenuController() throws ClassNotFoundException {
    }

    public void createEventButtonOnAction(ActionEvent event) {
    }

    public void cancelEventButtonOnAction(ActionEvent event) {
        OrganizerCancelEventAlertBox.display();
    }
}
