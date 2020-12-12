package UI;

import entities.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AttendeeSignedUpScheduleMenuController extends ScheduleMenuControllerA{
    @FXML
    private Button cancelButton;
    private String fxmlName = "AttendeeScheduleMenu.fxml";

    public AttendeeSignedUpScheduleMenuController() throws ClassNotFoundException {
    }

    public void cancelButtonOnAction(ActionEvent event) {
        Boolean confirmation = AttendeeCancelEventAlertBox.display();
        if (confirmation){
            String ename = eventTable.getSelectionModel().getSelectedItem().getName().getValue();

            Event e = mainModel.getEm().getEventByName(ename);

            e.removeAttendee(mainModel.getUserID());
            mainModel.getCurrentUser().removeEvent(e.getId());
            cancelEventLabel.setText("Cancellation Successful");
        }

    }
}
