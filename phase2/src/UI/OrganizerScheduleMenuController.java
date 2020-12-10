package UI;

import com.sun.tools.javac.util.Name;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OrganizerScheduleMenuController extends ScheduleMenuController implements Initializable {

    @FXML
    Button createEventButton;

    @FXML
    Button cancelEventButton;

    @FXML
    TableView eventTable;

    private String fxmlName = "OrganizerScheduleMenu.fxml";

    public OrganizerScheduleMenuController() throws ClassNotFoundException {
    }

    public void createEventButtonOnAction(ActionEvent event) {

    }

    public void cancelEventButtonOnAction(ActionEvent event) {
        OrganizerCancelEventAlertBox.display();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cancelEventButton.setDisable(true);
    }

    public void selectEventAction(MouseEvent mouseEvent) {
        cancelEventButton.setDisable(false);
        eventTable.getSelectionModel().getSelectedItem();
    }
}
