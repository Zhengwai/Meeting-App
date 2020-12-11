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

public abstract class GeneralController {
    @FXML
    protected Button goBackButton;

    protected Model mainModel;

    protected String previousScene;

    private String fxmlName;

    public GeneralController() throws ClassNotFoundException {
        mainModel = new Model();
    }
    protected void initData(Model mainModel){
        this.mainModel = mainModel;

    }
    /**
     * Closes this menu and goes back to the login screen.
     * @param event an event denoting the user's clicking action.
     */
    public void goBackButtonOnAction(ActionEvent event) throws IOException {
        goBackButton.getScene().getWindow().hide();
    }

    public void newButtonStage(String filePath, Button button) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(filePath));
        Stage stage = new Stage(); //sets stage.
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(button.getScene().getWindow());//this scene with the login button will be the owner of the stage.(Kinda like the root of a tree)
        stage.setScene(new Scene(loader.load())); //adds the menu scene to the stage.

        button.getScene().getWindow().hide();//temporarily close this window
        stage.showAndWait();//showAndWait will block execution until the window closes.
        Stage thisStage = (Stage) button.getScene().getWindow(); //get a reference to this stage.
        thisStage.show();// show the login screen again.
    }

}
