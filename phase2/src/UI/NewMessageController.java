package UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class NewMessageController extends GeneralController implements Initializable {
    @FXML
    Button sendButton;
    @FXML
    ChoiceBox chooseNewFriend;
    @FXML
    TextField messageBox;
    @FXML
    ListView messageHistory;


    public NewMessageController() throws ClassNotFoundException {
    }



    public void handleSendButton(ActionEvent actionEvent) {
        String newFriend = (String) chooseNewFriend.getValue();
        boolean nowFriends = mainModel.getUm().addFriends(mainModel.getCurrentUser().getID(),
                mainModel.getUm().getUserByName(newFriend).getID());
        String myMessage = messageBox.getText();
        messageHistory.getItems().setAll("Me: " + myMessage);

        //TODO: transition to chat after first message is sent
        //TODO: add functionality (link to message system)

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("does the controller " + mainModel.getCurrentUser().getUsername());
        List<String> notFriends = mainModel.getUm().getAllNonFriendNames(mainModel.getCurrentUser().getID());
        chooseNewFriend.getItems().setAll(notFriends);
    }
}
