package UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * A controller for the menu layout of an Attendee user.
 */
public class AttendeeMenuController extends MenuController implements Initializable {

    @FXML
    Label welcomeLabel;

    @FXML
    Button requestsButton;

    private String fxmlName = "AttendeeMenu.fxml";

    public AttendeeMenuController() throws ClassNotFoundException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        welcomeLabel.setText("Welcome " + mainModel.getCurrentUser().getUsername() + "!");
    }

    public void RequestButtonOnAction(ActionEvent actionEvent) throws IOException {
        showEvent("Requests.fxml");
    }
}
