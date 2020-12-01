package UI;
import entities.User;
import gateways.LoginGateway;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import use_cases.UserManager;

import javafx.event.ActionEvent;

import java.io.IOException;

abstract class GeneralController {
    @FXML
    protected Button goBackButton;

    protected Model mainModel;

    protected void initData(Model mainModel){
        this.mainModel = mainModel;

    }
    /**
     * Closes this menu and goes back to the login screen.
     * @param event an event denoting the user's clicking action.
     */
    public void goBackButtonOnAction(ActionEvent event) {
        goBackButton.getScene().getWindow().hide();
    }

}
