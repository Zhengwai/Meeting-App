package UI;

import entities.Event;
import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class AttendeeSignedUpScheduleMenuController extends ScheduleMenuController{

    @FXML
    Button cancelButton;
    @FXML
    Label cancelPrompt;
    @FXML
    Button refreshButton;
    
    private String fxmlName = "AttendeeSignedUpScheduleMenu.fxml";
    public AttendeeSignedUpScheduleMenuController() throws ClassNotFoundException {
        super();
        cancelPrompt.setDisable(true);
    }

    public void cancelButtonOnAction() {
        Boolean confirmation = AttendeeCancelEventAlertBox.display();
        if (confirmation){
            String ename = eventTable.getSelectionModel().getSelectedItem().getName().toString();
            Event e = mainModel.getEm().getEventByName(ename);
            e.removeAttendee(mainModel.getUserID());
            mainModel.getCurrentUser().removeEvent(e.getId());
        }
    }

    public void refreshButtonOnAction(){
        User user = mainModel.getCurrentUser();
        ArrayList<Event> evts = mainModel.getEm().getEventsByUser(user);
        setAllEvents(evts);
        initialize();
    }
}
