package UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

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
