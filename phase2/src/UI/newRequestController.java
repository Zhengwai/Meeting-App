package UI;

import entities.Request;
import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sun.security.x509.GeneralName;

import java.io.IOException;

public class newRequestController extends GeneralController {
    @FXML
    protected Button createRequestButton;
    @FXML
    protected TextField requestTextField;
    @FXML
    protected Label statusLabel;

    public newRequestController() throws ClassNotFoundException {
    }

    public void createRequestButtonOnAction(ActionEvent actionEvent) {
        if(requestTextField.getText().trim().equals("")){
            // TODO: 12/11/2020 implement when request is invalid
            statusLabel.setText("Request text cannot be empty");
            statusLabel.setVisible(true);

        }
        else{
            System.out.println("testing submit request");
            User user = mainModel.getCurrentUser();
            String requestText = requestTextField.getText();
            Request newRequest = new Request(user.getID(),requestText);
            mainModel.getRm().addRequest(newRequest);

            createRequestButton.getScene().getWindow().hide();


        }
    }
}


