package UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MessageMenuController extends GeneralController implements Initializable{

    @FXML
    Button newMessageButton;
    @FXML
    BorderPane messageMain;
    @FXML
    ComboBox chooseNewFriend;
    @FXML
    ListView myMessageList;

    private ObservableList<String>  friends = FXCollections.observableArrayList("Jenn","Joe","Sherry", "Eunice");

    public void handleNewMessage(ActionEvent actionEvent) throws IOException {
        FxmlLoaderMessage object = new FxmlLoaderMessage();
        Pane view = object.getPage("NewMessage.fxml");
        messageMain.setCenter(view);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        myMessageList.setItems(friends);
    }
}
