package UI;

import controllers.MessageControllerAdapter;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

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

    @FXML
    Button deleteMessages;

    @FXML
    HBox actionButtons;

    @FXML
    HBox sendBox;

    @FXML
    Button archiveButton;

    @FXML
    Button unreadButton;

    ConversationHolder ch = ConversationHolder.getInstance();

    ObservableList<String> conversation;

    MessageBuilder mb = new MessageBuilder();

    MessageControllerAdapter mca = new MessageControllerAdapter(mainModel.getUserID(), mainModel.getCm(), mainModel.getUm(), mainModel.getEm());

    public ChatController() throws ClassNotFoundException {
    }

    /**
     * Initializes the chat automatically when the chat is opened.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(mainModel.getCm().conversationArchived(ch.getConversation(), mainModel.getUserID())){
            deleteMessages.setDisable(true);
            archiveButton.setText("Unarchive");
            sendBox.setVisible(false);
            unreadButton.setDisable(true);

        }

        chatPane.setVisible(true);
        deleteMessages.setDisable(true);

        if(mainModel.getCm().getReadOnly(ch.getConversation())){
            messageBox.setDisable(true);
        }

        sendeeLabel.setText(ch.getConversationName());
        updateMessageHistory();

    }

    /**
     * Handles the sending of the message upon pressing the send button.
     * @param actionEvent
     */
    public void handleSend(ActionEvent actionEvent) {
        String messageBody = messageBox.getText();
        mca.MessageConversation(ch.getConversation(), messageBody);

        updateMessageHistory();
        messageBox.setText("");

    }

    /**
     * Handles the archiving/unarchiving of a conversation depending on whether it is already archived or not.
     * @param actionEvent pressing the archiveButton button.
     */
    public void handleArchive(ActionEvent actionEvent) {
        if(archiveButton.getText().equals("Unarchive")){
            mca.UnarchiveConversation(ch.getConversation());
            deleteMessages.setDisable(false);
            sendBox.setVisible(true);
            unreadButton.setDisable(false);
            archiveButton.setText("Archive");
        }else if(archiveButton.getText().equals("Archive")){
            mca.ArchiveConversation(ch.getConversation());
            deleteMessages.setDisable(true);
            unreadButton.setDisable(true);
            archiveButton.setText("Unarchive");
            sendBox.setVisible(false);
        }
    }

    /**
     * Handles marking the conversation as unread.
     * @param actionEvent
     */
    public void markUnread(ActionEvent actionEvent) {
        mca.MarkUnread(ch.getConversation());
        chatPane.setVisible(false);
    }

    /**
     * Handles deleting the selected messages.
     * @param actionEvent
     */
    public void handleDelete(ActionEvent actionEvent) {
        String[] thisOne = (String[]) messageHistory.getSelectionModel().getSelectedItem();
        UUID message = UUID.fromString(thisOne[3]);
        mca.DeleteMessage(message, ch.getConversation());
        updateMessageHistory();
        deleteMessages.setDisable(true);
    }

    /**
     * Enables the delete messages button once a message that the user sent from the message history is selected.
     * @param mouseEvent
     */
    public void handleSelect(MouseEvent mouseEvent) {
        String[] thisOne = (String[]) messageHistory.getSelectionModel().getSelectedItem();
        if(thisOne[0].equals(mainModel.getCurrentUser().getID().toString()))
            deleteMessages.setDisable(false);

    }

    /**
     * Updates the message history with new messages.
     */
    public void updateMessageHistory(){
        messageHistory.getItems().setAll(mca.getMessagesInConversation(ch.getConversation()));
        messageHistory.setCellFactory(param -> new ListCell<String[]>() {
            protected void updateItem(String[] item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item[0] == null || item[1] == null || item[2] == null) {
                    setText(null);
                } else {

                    // set the width's
                    setMinWidth(386.0);
                    setMaxWidth(386.0);
                    setPrefWidth(386.0);

                    // allow wrapping
                    setWrapText(true);

                    if(item[0].equals(mainModel.getUserID().toString()))
                        setText("Me: " + item[2]);
                    else
                        setText(item[0] + ": " + item[2]);


                }
            }
        });
    }
}
