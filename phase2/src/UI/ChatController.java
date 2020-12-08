package UI;

import com.sun.org.apache.xml.internal.security.Init;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

public class ChatController extends GeneralController implements Initializable {
    @FXML
    Label sendeeLabel;

    @FXML
    TextField messageBox;

    @FXML
    Button sendButton;

    @FXML
    ListView messageHistory;

    @FXML
    BorderPane chatPane;

    ConversationHolder ch = ConversationHolder.getInstance();

    ObservableList<String> conversation;

    MessageBuilder mb = new MessageBuilder();

    public ChatController() throws ClassNotFoundException {
    }

    public void handleSend(ActionEvent actionEvent) {
        String myMessage = messageBox.getText();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //sendeeLabel.setText(mainModel.getUm().getUserByID(sendee.getSendee()).getUsername());
        messageHistory.getItems().setAll(mb.buildChatContents(mainModel.getCm().
                getUserUnreadMessages(ch.getConversation(), mainModel.getCurrentUser().getID())));
    }
}
