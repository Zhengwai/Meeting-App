package UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * A controller for the menu layout of an Organizer user.
 */
public class OrganizerMenuController extends MenuController implements Initializable{
    @FXML
    Button createButton;

    @FXML
    Label welcomeLabel;


    private String fxmlName = "OrganizerMenu.fxml";

    public OrganizerMenuController() throws ClassNotFoundException {
    }


    /**
     * Opens up a new scene/stage for organizer to create new users.
     * Returns back to this scene when done.
     * @param event an event denoting the user's clicking action.
     */
    public void createButtonOnAction(ActionEvent event) throws IOException {
        //TODO:
        showEvent("OrganizerCreateAccount.fxml");
    }

    public void OrganizerRequestButtonOnAction(ActionEvent actionEvent) throws IOException {
        System.out.println("test request menu");
        showEvent("OrganizerRequests.fxml");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        welcomeLabel.setText("Welcome " + mainModel.getCurrentUser().getUsername() + "!");
    }




    //public void handleMessageAction(ActionEvent actionEvent) throws IOException {
    //    showEvent("MainMessage.fxml");
    //}
}