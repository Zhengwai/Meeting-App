package UI;

import entities.Speaker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class SelectSpeakerController extends GeneralController implements Initializable {
    @FXML
    ChoiceBox chooseSpeakerBox;
    @FXML
    Button confirmSpeakerButton;
    @FXML
    Button addAnotherSpeakerButton;
    @FXML
    Label successLabel;
    @FXML
    ListView addedSpeakers;

    EventHolder eh = EventHolder.getInstance();

    public SelectSpeakerController() throws ClassNotFoundException {
    }

    public void confirmSpeakerButton(ActionEvent actionEvent) {
        String speaker = (String) chooseSpeakerBox.getSelectionModel().getSelectedItem();
        mainModel.getEm().assignSpeaker(eh.getEvent(), speaker);
        String type = mainModel.getEm().getEventByID(eh.getEvent()).getType().getValue();
        System.out.println(type);
        successLabel.setText(speaker + "was successfully \n assigned to " + eh.getEventName());
        addedSpeakers.getItems().add(speaker);
        successLabel.setVisible(true);
        confirmSpeakerButton.setDisable(true);
        chooseSpeakerBox.setDisable(true);

        if(type.equals("TED")){
            addAnotherSpeakerButton.setDisable(false);
        }
    }

    public void addAnotherAction(ActionEvent actionEvent) {
        updateSpeakers();
        successLabel.setVisible(false);
        chooseSpeakerBox.setDisable(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateSpeakers();
        successLabel.setVisible(false);

    }

    public void isChosen(ActionEvent actionEvent) {
        if(!chooseSpeakerBox.getSelectionModel().isEmpty()){
            confirmSpeakerButton.setDisable(false);
        }
    }

    public void updateSpeakers(){
        chooseSpeakerBox.getItems().setAll(mainModel.getEm().getAvailableSpeakers(eh.getEvent()));
    }
}
