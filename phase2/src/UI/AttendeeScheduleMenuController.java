package UI;

import entities.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.util.UUID;

public class AttendeeScheduleMenuController extends ScheduleMenuController{
    private String fxmlName = "AttendeeScheduleMenu.fxml";

    public AttendeeScheduleMenuController() throws ClassNotFoundException {
    }

    public void signUpButtonOnAction(ActionEvent event) {
        Boolean confirmation = AttendeeSignUpEventAlertBox.display();
        if (confirmation) {
            String ename = eventTable.getSelectionModel().getSelectedItem().getName().getValue();

            Event e = mainModel.getEm().getEventByName(ename);

            e.addAttendee(mainModel.getUserID());
            mainModel.getUm().getUserByID(mainModel.getCurrentUser().getID()).addEvent(e.getId());
            signUpPrompt.setText("Signed Up Successfully!");
        }
    }
}
