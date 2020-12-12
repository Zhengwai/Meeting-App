package UI;

import entities.Event;
import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.UUID;

public class AttendeeScheduleMenuController extends ScheduleMenuController{
    private String fxmlName = "AttendeeScheduleMenu.fxml";
    @FXML
    protected Button signUpButton;
    protected Label signUpPrompt= new Label();
    public AttendeeScheduleMenuController() throws ClassNotFoundException {
    }
    public void signUpButtonOnAction() {
        Boolean confirmation = AttendeeSignUpEventAlertBox.display();
        if (confirmation) {
            Event e = mainModel.getEm().getEventByName(getEvtSelected());
            e.addAttendee(mainModel.getUserID());
            User user = mainModel.getCurrentUser();
            user.addEvent(e.getId());
            mainModel.setCurrentUser(user);
            signUpPrompt.setText("Signed Up Successfully!");
        }
    }
}
