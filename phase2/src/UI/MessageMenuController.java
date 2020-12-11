package UI;

import UI.ConversationHolder;
import UI.GeneralController;
import UI.MessageBuilder;
import controllers.MessageControllerAdapter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MessageMenuController extends GeneralController implements Initializable, PropertyChangeListener{

    @FXML
    Button newMessageButton;
    @FXML
    BorderPane messageMain;
    @FXML
    ChoiceBox chooseNewFriend;
    @FXML
    ListView myMessageList;
    @FXML
    BorderPane subPane;
    @FXML
    TextField message;
    @FXML
    ListView messageHistory;
    @FXML
    ChoiceBox filterMessages;
    @FXML
    ChoiceBox newMessageType;
    @FXML
    Button sendButton;

    ConversationHolder ch = ConversationHolder.getInstance();

    MessageBuilder mb = new MessageBuilder();

    ObservableList<String> notFriends;

    List<String> filterOptions = new ArrayList<String>(Arrays.asList("All Messages", "Unread Messages", "Archived Messages"));

    MessageControllerAdapter mca = new MessageControllerAdapter(mainModel.getCurrentUser().getID(), mainModel.getCm(),
            mainModel.getUm(), mainModel.getEm());

    private ObservableList<String> conversations;

    public MessageMenuController() throws ClassNotFoundException {
        mainModel.getCm().addObserver(this);
    }

    /**
     * Sets up GUI for sending a new message.
     * @param actionEvent occurs when "New Message" button is clicked.
     * @throws IOException
     */
    public void handleNewMessage(ActionEvent actionEvent) throws IOException {
        buildNewMessage();
        sendButton.setDisable(true);
        messageMain.setCenter(subPane);
        messageHistory.getItems().clear();
        subPane.setVisible(true);
    }

    //Put in builder DP
    public void buildNewMessage() {
        notFriends = FXCollections.observableList(
                mainModel.getUm().getAllNonFriendNames(mainModel.getCurrentUser().getID()));
        chooseNewFriend.getItems().setAll(notFriends);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        filterMessages.getItems().setAll(filterOptions);

        //Can maybe go in builder
        if (mainModel.getCurrentUser().getType().equals("s")) {
            newMessageType.getItems().setAll("Speaking Event", "Users");
            newMessageType.setDisable(false);
        } else if (mainModel.getCurrentUser().getType().equals("o")) {
            newMessageType.getItems().setAll("Users", "All Speakers", "All Users");
            newMessageType.setDisable(false);
        }

        updateFilterMessages();

        subPane.setVisible(false);
    }


    public void handleSelectChat(MouseEvent mouseEvent) {
        if(!(myMessageList.getSelectionModel().getSelectedItem() == null)) {
            String[] recipient = (String[]) myMessageList.getSelectionModel().getSelectedItem();
            ch.setConversation(UUID.fromString(recipient[1]));
            ch.setConversationName(recipient[0]);
            messageMain.setCenter(mb.chatBuilder());
        }
    }

    public void handleSendNewAction(ActionEvent actionEvent) {
        String choice = (String) newMessageType.getValue();
        String myMessage = message.getText();
        String title;

        if (choice.equals("Speaking Event")) {
            String newFriend = (String) chooseNewFriend.getValue();
            mca.MessageAllEventAttendees(myMessage, newFriend,
                    mainModel.getEm().getEventByName((String) chooseNewFriend.getValue()).getId());
        } else if (choice.equals("All Speakers")) {
            title = "Notice from Organizer";
            mca.MessageAllSpeakers(myMessage, title);
        } else if (choice.equals("All Users")) {
            title = "Notice from Organizer";
            mca.MessageAllAttendees(myMessage, title);
        } else {
            String newFriend = (String) chooseNewFriend.getValue();
            mca.AddFriend(mainModel.getUm().getUserByName(newFriend).getID(), myMessage);
        }

        messageMain.setCenter(mb.chatBuilder());

        this.message.setText("");
        sendButton.setDisable(true);

        updateFilterMessages();
    }


    public void folderByCategory(ActionEvent actionEvent) {
        String choice = (String) newMessageType.getValue();
        if (choice.equals("Speaking Event")) {
            chooseNewFriend.setDisable(false);
            chooseNewFriend.getItems().setAll(mb.buildSpeakingEvent());
            sendButton.setDisable(true);
        }
        if (choice.equals("All Speakers") | choice.equals("All Users")) {
            chooseNewFriend.setDisable(true);
            sendButton.setDisable(false);
        }
        if (choice.equals("Users")) {
            chooseNewFriend.setDisable(false);
            sendButton.setDisable(true);
            buildNewMessage();
        }
    }

    public void isChosen(ActionEvent actionEvent) {
        if (chooseNewFriend.getValue() != null) {
            sendButton.setDisable(false);
        }
    }

    public void handleFilterMessages(ActionEvent actionEvent) {
        updateFilterMessages();
    }

    public void updateFilterMessages() {
        ArrayList<String[]> filteredMessages = new ArrayList<>();
        String choice = (String) filterMessages.getValue();
        List<String> messageNames = new ArrayList<>();

        if (choice.equals("All Messages")) {
            filteredMessages = mca.getConversations();
            System.out.println("All");
        }
        else if (choice.equals("Unread Messages")) {
            filteredMessages = mca.getUnreadConversations();
            System.out.println("Unread");
        }
        else if (choice.equals("Archived Messages")) {
            filteredMessages = mca.getArchivedConversations();
            System.out.println("Archived");
        }

        if (filteredMessages.size() > 0) {
            myMessageList.getItems().setAll(filteredMessages);

            myMessageList.setCellFactory(param -> new ListCell<String[]>() {
                protected void updateItem(String[] item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null || item[0] == null || item[1] == null) {
                        setText(null);
                    } else {
                        setText(item[0]);
                    }
                }
            });
        } else if(filteredMessages.size() == 0){
            System.out.println("Am Empty:)");
            myMessageList.getItems().clear();
            myMessageList.setPlaceholder(new Label("No Messages"));
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("Hello");
        updateFilterMessages();
    }
}
