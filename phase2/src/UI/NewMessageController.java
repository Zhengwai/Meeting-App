package UI;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import javax.security.auth.Subject;
import java.net.URL;
import java.util.*;

public class NewMessageController extends GeneralController implements Initializable {
    @FXML
    Button sendButton;
    @FXML
    ChoiceBox chooseNewFriend;
    @FXML
    TextField messageBox;
    @FXML
    ListView messageHistory;

    ObservableList<String> notFriends;

    List<Observer> observers;

    MessageMenuController mmc = new MessageMenuController();

    public NewMessageController() throws ClassNotFoundException {
    }

    public void updateNotFriends(String newFriend){
        notFriends.remove(newFriend);
        chooseNewFriend.getItems().setAll(notFriends);

    }

    public void handleSendButton(ActionEvent actionEvent) {
        String newFriend = (String) chooseNewFriend.getValue();
        boolean nowFriends = mainModel.getUm().addFriends(mainModel.getCurrentUser().getID(),
                mainModel.getUm().getUserByName(newFriend).getID());
        String myMessage = messageBox.getText();
        messageHistory.getItems().setAll("Me: " + myMessage);

        if(mainModel.getUm().getAllFriendNames(mainModel.getCurrentUser().getID()).size() > 0){
            for(String s: mainModel.getUm().getAllFriendNames(mainModel.getCurrentUser().getID())){
                System.out.println(s);
            }
        } else{
            System.out.println("Friend List is Empty");
        }

        updateNotFriends(newFriend);

        //TODO: transition to chat after first message is sent
        //TODO: add functionality (link to message system)

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        notFriends = FXCollections.observableList(
                mainModel.getUm().getAllNonFriendNames(mainModel.getCurrentUser().getID()));
        chooseNewFriend.getItems().setAll(notFriends);

        notFriends.addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change c) {
                System.out.println("Detected a change!");
                while(c.next()){
                    if(c.wasRemoved()){

                    }
                }
            }
        });


    }
}
