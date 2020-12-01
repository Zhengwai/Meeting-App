package UI;

import gateways.LoginGateway;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import use_cases.UserManager;

import javafx.event.ActionEvent;

public class AttendeeMenuController {
    @FXML
    Button goBackButton;
    @FXML
    Button browseButton;
    @FXML
    Button seeSignedUpButton;

    public void goBackButtonOnAction(ActionEvent event) {
        goBackButton.getScene().getWindow().hide();
    }
}
