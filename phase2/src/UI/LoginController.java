package UI;
import entities.User;
import gateways.LoginGateway;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import use_cases.EventManager;
import use_cases.UserManager;
import use_cases.ConversationManager;

import javafx.event.ActionEvent;

import java.io.IOException;

/**
 * A controller for a scene that handles login procedure.
 */
public class LoginController extends GeneralController{

    LoginGateway lg = new LoginGateway();

    @FXML
    private Button loginButton;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button registerButton;
    @FXML
    private Label promptLabel;

    private Model mainModel = new Model();

    private String fxmlName = "login.fxml";

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
    public void loginButtonOnAction(ActionEvent event) throws IOException, ClassNotFoundException {
        //verifies the login credentials through main model and stores the user, stores null if credentials are incorrect.
        User user = mainModel.getUm().verifyLogin(usernameTextField.getText(), passwordField.getText());
        if (user != null) { //correct credentials.
            mainModel.setCurrentUser(user); //Sets this user as current user in main model.
            promptLabel.setText(""); //Clears the error message label.
            String type = (user.getType()); //gets the user's type, launches different menus depending on type.
            System.out.println("test print");
            if (type.equals("a") | type.equals("v")){
                newButtonStage("AttendeeMenu.fxml", loginButton);
            } else if (type.equals("o")){
                newButtonStage("OrganizerMenu.fxml", loginButton);
            } else if (type.equals("s")) {
                newButtonStage("SpeakerMenu.fxml", loginButton);
            }

            usernameTextField.setText("");
            passwordField.setText("");

        } else {
            promptLabel.setVisible(true);
            promptLabel.setText("Username or password incorrect, please try again"); //prompts error message.
        }
    }


    public void registerButtonAction(ActionEvent event) throws IOException {
        newButtonStage("register.fxml", loginButton);
        usernameTextField.setText("");
        passwordField.setText("");
    }





}
