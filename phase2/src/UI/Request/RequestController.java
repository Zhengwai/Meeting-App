package UI.Request;

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

    public void newRequestButtonOnAction(javafx.event.ActionEvent actionEvent) {
        User user =  mainModel.getCurrentUser();
        if (user.getType() == "a") {

        }
        // TODO: 12/10/2020 implement
    }

    public void viewRequestButtonOnAction(ActionEvent actionEvent) {


    }
}
