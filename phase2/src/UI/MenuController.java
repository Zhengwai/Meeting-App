package UI;
import entities.User;
import gateways.LoginGateway;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

abstract class MenuController extends GeneralController{
    @FXML
    Button seeSignedUpButton;
    @FXML
    protected Button browseButton;
    @FXML
    Button seeMessageButton;
    @FXML
    Label welcomeLabel;

    private String fxmlName;

    public MenuController() throws ClassNotFoundException {
    }

    public void seeSignedUpButtonOnAction(ActionEvent event) throws IOException {
        //TODO: Implement this, you can get the current user by using mainModel.getCurrentUser. Open different type of
        //TODO: scene depending on the user's type.
        String type = mainModel.getCurrentUser().getType();
        if (type.equals("a")|type.equals("v")){
            showEvent("AttendeeSignedUpScheduleMenu.fxml");
        }else if (type.equals("o")){
            showEvent("OrganizerSignedUpScheduleMenu.fxml");
        }else if (type.equals("s")){
            showEvent("SpeakerAssignedScheduleMenu.fxml");
        }
    }

    /**
     * Opens up a new scene/stage for the user to browse events available.
     * Scene/stage opened will vary depending on the type of user.
     * Returns back to this scene when done.
     * @param event an event denoting the user's clicking action.
     */
    public void browseButtonOnAction(ActionEvent event) throws IOException {
        //TODO: Implement this, you can get the current user by using mainModel.getCurrentUser. Open different type of
        //TODO: scene depending on the user's type.
        String type = mainModel.getCurrentUser().getType();
        if (type.equals("a")|type.equals("v")) {
            showEvent("AttendeeScheduleMenu.fxml");
        } else if (type.equals("o")){
            showEvent("OrganizerScheduleMenu.fxml");
        } else if (type.equals("s")){
            showEvent("SpeakerScheduleMenu.fxml");
        }

    }

    public void seeMessageAction(ActionEvent event) throws IOException{
        System.out.println(mainModel.getCurrentUser().getUsername());
        showEvent("MainMessage.fxml");
    }

    public void showEvent(String filePath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(filePath));
        Stage stage = new Stage(); //sets stage.
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(browseButton.getScene().getWindow());//this scene with the login button will be the owner of the stage.(Kinda like the root of a tree)
        stage.setScene(new Scene((Parent) loader.load())); //adds the menu scene to the stage.
        //GeneralController controller = loader.getController(); //stores the controller of the menu scene
        //controller.initData(mainModel); //Pass the mainModel, storing information, to the menu's controller.



        /*browseButton.getScene().getWindow().hide();//temporarily close this window*/
        stage.showAndWait();//showAndWait will block execution until the window closes.
        Stage thisStage = (Stage) browseButton.getScene().getWindow(); //get a reference to this stage.
        thisStage.show();// show the login screen again.
    }
}
