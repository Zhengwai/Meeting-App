package UI;

import UI.GeneralController;
import UI.Model;
import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLOutput;

public class RequestController extends GeneralController {

    @FXML
    protected Button newRequestButton;

    private String fxmlName = "Requests.fxml";

    public RequestController() throws ClassNotFoundException {

    }

    public void newRequestButtonOnAction(javafx.event.ActionEvent actionEvent) throws IOException {
        System.out.println("NewButtonTest");
        newButtonStage("newRequest.fxml", newRequestButton);
    }


}
