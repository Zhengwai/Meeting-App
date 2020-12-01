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

/**
 * A controller for a scene that handles login procedure.
 */
public class LoginController {

    LoginGateway lg = new LoginGateway();

    @FXML
    private Button loginButton;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Button registerButton;
    @FXML
    private Label promptLabel;

    private Model mainModel = new Model();

    public LoginController() throws ClassNotFoundException {
    }

    /**
     * Checks the user's credentials and log them in if correct, prompts error message if wrong.
     * When logged in, this scene will temporarily disappear.
     * Different types of user get different types menu scene.
     * This scene re-appears when the user exits the menu scene.
     * @param event an event denoting the user's clicking action.
     * @throws IOException
     */
    public void loginButtonOnAction(ActionEvent event) throws IOException {
        //verifies the login credentials through main model and stores the user, stores null if credentials are incorrect.
        User user = mainModel.getUm().verifyLogin(usernameTextField.getText(), passwordTextField.getText());
        if (user != null) { //correct credentials.
            mainModel.setCurrentUser(user); //Sets this user as current user in main model.
            promptLabel.setText(""); //Clears the error message label.
            String type = (user.getType()); //gets the user's type, launches different menus depending on type.
            if (type.equals("a") | type.equals("v")){
                showMenu("AttendeeMenu.fxml");
            } else if (type.equals("o")){
                showMenu("OrganizerMenu.fxml");
            } else if (type.equals("s")) {
                showMenu("SpeakerMenu.fxml");
            }

        } else {
            promptLabel.setText("Username or password incorrect, please try again"); //prompts error message.
        }
    }

    private void showMenu(String filePath) throws IOException{
        //Gets the loader with the specific menu's fxml path.
        FXMLLoader loader = new FXMLLoader(getClass().getResource(filePath));
        Stage stage = new Stage(); //sets stage.
        stage.initOwner(loginButton.getScene().getWindow());//this scene with the login button will be the owner of the stage.(Kinda like the root of a tree)
        stage.setScene(new Scene((Parent) loader.load())); //adds the menu scene to the stage.
        MenuController controller = loader.getController(); //stores the controller of the menu scene
        controller.initData(mainModel); //Pass the mainModel, storing information, to the menu's controller.



        loginButton.getScene().getWindow().hide();//temporarily close this window
        stage.showAndWait();//showAndWait will block execution until the window closes.
        Stage thisStage = (Stage) loginButton.getScene().getWindow(); //get a reference to this stage.
        thisStage.show();// show the login screen again.
    }





}
