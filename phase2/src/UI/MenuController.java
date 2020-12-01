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

abstract class MenuController {
    @FXML
    protected Button goBackButton;
    @FXML
    protected Button browseButton;
    @FXML
    Button seeSignedUpButton;

    Model mainModel;

    void initData(Model mainModel){
        this.mainModel = mainModel;

    }

    public void goBackButtonOnAction(ActionEvent event) {
        goBackButton.getScene().getWindow().hide();
    }

    public void browseButtonOnAction(ActionEvent event){
        //TODO:
    }

    public void seeSignedUpButtonOnAction(ActionEvent event){
        //TODO:
    }
}
