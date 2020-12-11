package UI;

import UI.GeneralController;
import UI.Model;
import entities.User;
import gateways.LoginGateway;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class RequestController extends GeneralController {
    LoginGateway lg = new LoginGateway();

    @FXML
    private Button newRequestButton;

    private String fxmlName = "Requests.fxml";

    public RequestController() throws ClassNotFoundException {

    }

    public void newRequestButtonOnAction(javafx.event.ActionEvent actionEvent) throws IOException {
        newButtonStage("EditRequest.fxml", newRequestButton);
    }

    public void viewRequestButtonOnAction(ActionEvent actionEvent) {


    }
}
