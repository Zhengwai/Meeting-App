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

    public LoginController() throws ClassNotFoundException {
    }

    public void loginButtonOnAction(ActionEvent event) throws IOException {
        for (User u:mainModel.getUm().getAllUsers()){
            System.out.println("This user:");
            System.out.println(u.getUsername());
            System.out.println(u.getPassword());
        }
        System.out.println(mainModel.getUm());
        System.out.println(usernameTextField.getText());
        System.out.println(passwordTextField.getText());
        User user = mainModel.getUm().verifyLogin(usernameTextField.getText(), passwordTextField.getText());
        if (user != null) {
            String type = (user.getType());
            if (type.equals("a") | type.equals("v")){
                showAttendeeMenu();
            } else if (type.equals("o")){
                showOrganizerMenu();
            } else if (type.equals("s")) {
                showSpeakerMenu();
            }

        } else {
            promptLabel.setText("Username or password incorrect, please try again");
        }
    }
    //TODO: I know these 3 methods below are repetitive, I will refactor some of the controllers with an abstract contr-
    //      oller so that these 3 methods could be combined. Just trying to get things working rn.

    private void showSpeakerMenu() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SpeakerMenu.fxml"));
        Stage stage = new Stage();
        stage.initOwner(loginButton.getScene().getWindow());
        stage.setScene(new Scene((Parent) loader.load()));
        SpeakerMenuController controller = loader.getController();
        controller.initData(mainModel);


        // showAndWait will block execution until the window closes...
        loginButton.getScene().getWindow().hide();
        stage.showAndWait();
        Stage thisStage = (Stage) loginButton.getScene().getWindow();
        thisStage.show();
    }

    private void showOrganizerMenu() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OrganizerMenu.fxml"));
        Stage stage = new Stage();
        stage.initOwner(loginButton.getScene().getWindow());
        stage.setScene(new Scene((Parent) loader.load()));
        OrganizerMenuController controller = loader.getController();
        controller.initData(mainModel);


        // showAndWait will block execution until the window closes...
        loginButton.getScene().getWindow().hide();
        stage.showAndWait();
        Stage thisStage = (Stage) loginButton.getScene().getWindow();
        thisStage.show();
    }

    public void showAttendeeMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AttendeeMenu.fxml"));
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
