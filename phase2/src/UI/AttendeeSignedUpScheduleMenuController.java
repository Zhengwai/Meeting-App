package UI;

import entities.Event;
import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class AttendeeSignedUpScheduleMenuController extends ScheduleMenuControllerA{
    @FXML
    protected Button cancelButton;
    @FXML
    protected Label cancelEventLabel = new Label();
    private String fxmlName = "AttendeeScheduleMenu.fxml";

    public AttendeeSignedUpScheduleMenuController() throws ClassNotFoundException {
    }

    public void cancelButtonOnAction(ActionEvent event) {
        Boolean confirmation = AttendeeCancelEventAlertBox.display();
        if (confirmation){

            mainModel.getEm().getEventByName(getEvtSelected()).removeAttendee(mainModel.getCurrentUser().getID());

            Event e = mainModel.getEm().getEventByName(getEvtSelected());

            e.removeAttendee(mainModel.getUserID());
            User user = mainModel.getCurrentUser();
            user.removeEvent(e.getId());
            mainModel.setCurrentUser(user);
            cancelEventLabel.setText("Cancellation Successful");
            initialize();

        }

    }
}
