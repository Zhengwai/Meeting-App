package UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * A controller for the menu layout of an Speaker user.
 */
public class SpeakerMenuController extends MenuController implements Initializable {

    @FXML
    Button seeAssignedButton;

    private String fxmlName = "SpeakerMenu.fxml";

    public SpeakerMenuController() throws ClassNotFoundException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        welcomeLabel.setText("Welcome " + mainModel.getCurrentUser().getUsername() + "!");
    }

}
