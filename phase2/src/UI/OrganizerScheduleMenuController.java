package UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OrganizerScheduleMenuController extends ScheduleMenuController {

    @FXML
    Button createEventButton;

    @FXML
    Button cancelEventButton;

    @FXML
    TableView eventTable;

    private String fxmlName = "OrganizerScheduleMenu.fxml";

    public OrganizerScheduleMenuController() throws ClassNotFoundException {
    }

    public void createEventButtonOnAction(ActionEvent event) throws IOException {
        showEvent("OrganizerCreateEvent.fxml");
        initialize();
    }

    public void cancelEventButtonOnAction(ActionEvent event) throws IOException {
        showEvent("OrganizerCancelEvent.fxml");
        //OrganizerCancelEventAlertBox.display();
        initialize();
    }
    public void showEvent(String filePath) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(filePath));
        Stage stage = new Stage(); //sets stage.
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(createEventButton.getScene().getWindow());
        stage.setScene(new Scene((Parent) loader.load()));
        GeneralController controller = loader.getController();
        controller.initData(mainModel);
        stage.showAndWait();
        Stage thisStage = (Stage) createEventButton.getScene().getWindow();
        thisStage.show();
    }
    public void selectEventAction(MouseEvent mouseEvent) {
        if(!(eventTable.getSelectionModel().getSelectedItem() == null)) {
            cancelEventButton.setDisable(false);
            eventTable.getSelectionModel().getSelectedItem();
        }
    }
}
