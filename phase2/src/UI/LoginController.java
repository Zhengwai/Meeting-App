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

    public void loginButtonOnAction(ActionEvent event) throws IOException {
        //Check Username and Password:
        if (lg.verifyLogin(usernameTextField.getText(), passwordTextField.getText()) != null) {
            //TODO: implement what to do after the user is verified to login.
            //TODO: first decide on what type of user it is, them open the associated window.
            String type = (lg.verifyLogin(usernameTextField.getText(), passwordTextField.getText())).getType();
            if (type.equals("a") | type.equals("v")){
                showMenu("AttendeeMenu.fxml");
            } else if (type.equals("o")){
                //TODO
            } else if (type.equals("s")) {
                //TODO
            }

        } else {
            promptLabel.setText("Username or password incorrect, please try again");
        }
    }
    public void showMenu(String filePath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(filePath));
        Stage stage = new Stage();
        stage.initOwner(loginButton.getScene().getWindow());
        stage.setScene(new Scene((Parent) loader.load()));
        AttendeeMenuController controller = loader.getController();
        controller.initData(mainModel);


        // showAndWait will block execution until the window closes...
        loginButton.getScene().getWindow().hide();
        stage.showAndWait();
        Stage thisStage = (Stage) loginButton.getScene().getWindow();
        thisStage.show();
    }




}
