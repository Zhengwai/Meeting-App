package UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class OrganizerMenuController {
    @FXML
    Button goBackButton;
    @FXML
    Button browseButton;
    @FXML
    Button seeSignedUpButton;
    @FXML
    Button createButton;

    Model mainModel;
    void initialize(){}

    void initData(Model mainModel){
        this.mainModel = mainModel;

    }

    public void goBackButtonOnAction(ActionEvent event) {
        goBackButton.getScene().getWindow().hide();
    }

    public void browseButtonOnAction(ActionEvent event){
    //TODO:
    }

    public void seeSignedUpButtonOnAction(ActionEvent event){
        //TODO:
    }

    public void createButtonOnAction(ActionEvent event){
        //TODO:
    }
}