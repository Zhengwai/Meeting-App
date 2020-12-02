package UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class
OrganizerCreateAccountController extends RegisterController {
    @FXML
    ComboBox<String> selectTypeAccountComboBox;

    ObservableList<String> accountTypeList = FXCollections.observableArrayList("Attendee", "Organizer", "Speaker");
    @FXML
    private void initialize(){
        selectTypeAccountComboBox.setItems(accountTypeList);
    }


}
