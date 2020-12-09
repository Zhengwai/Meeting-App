package UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MessageMenuController extends GeneralController implements Initializable {

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

    ContextMenu contextMenu;

    ConversationHolder ch = ConversationHolder.getInstance();

    MessageBuilder mb = new MessageBuilder();

    ObservableList<String> notFriends;

    List<String> filterOptions = new ArrayList<String>(
            Arrays.asList("All Messages", "Unread Messages", "Archived Messages"));

    private ObservableList<String> conversations;

    public MessageMenuController() throws ClassNotFoundException {
    }

    public void handleNewMessage(ActionEvent actionEvent) throws IOException {
        buildNewMessage();
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
        myMessageList.setContextMenu(mb.buildContextMenu());
        filterMessages.getItems().setAll(filterOptions);

        //Can maybe go in builder
        if(mainModel.getCurrentUser().getType().equals("s")){
            newMessageType.getItems().setAll("Speaking Event", "Users");
            newMessageType.setDisable(false);
        } else if(mainModel.getCurrentUser().getType().equals("o")){
            newMessageType.getItems().setAll("Speaking Event", "Users", "All Speakers", "All Users");
            newMessageType.setDisable(false);
        }

        UUID user = mainModel.getCurrentUser().getID();
        conversations = FXCollections.observableList(mb.buildMyConversations(mainModel.getCm().getUserConversationsNotArchived(user)));
        if (conversations.size() > 0) {
            myMessageList.getItems().setAll(conversations);
        } else {
            myMessageList.setPlaceholder(new Label("No Messages"));
        }
        subPane.setVisible(false);
    }

    public void handleSelectChat(MouseEvent mouseEvent) {
        if(mouseEvent.getButton() == MouseButton.SECONDARY){
            String recipienttest = (String) myMessageList.getSelectionModel().getSelectedItem();
            System.out.println("Right click :)" + recipienttest);
            contextMenu = new ContextMenu();
        } else {
            String recipient = (String) myMessageList.getSelectionModel().getSelectedItem();
            ch.setConversation(mainModel.getUm().getUserByName(recipient).getID());
            messageMain.setCenter(mb.chatBuilder());
        }
    }

    public void handleSendNewAction(ActionEvent actionEvent) {
        String choice = (String) newMessageType.getValue();

        if(choice.equals("Speaking Event")){

        } else if (choice.equals("All Speakers")){

        } else if (choice.equals("All Users")){

        }
        String newFriend = (String) chooseNewFriend.getValue();
        System.out.println(newFriend);
        String myMessage = message.getText();
        messageHistory.getItems().setAll("Me: " + myMessage);
        mainModel.getUm().addFriends(mainModel.getCurrentUser().getID(),
                mainModel.getUm().getUserByName(newFriend).getID());

        UUID conID = mainModel.getCm().newConversation();
        mainModel.getCm().addUserToConversation(conID, mainModel.getCurrentUser().getID());
        mainModel.getCm().addUserToConversation(conID, mainModel.getUm().getUserByName(newFriend).getID());

        ch.setConversation(conID);
        messageMain.setCenter(mb.chatBuilder());

        this.message.setText("");

        updateLists(newFriend);
    }

    public void updateLists(String friend){
        conversations.add(friend);
        notFriends.remove(friend);
        chooseNewFriend.getItems().setAll(notFriends);
        myMessageList.getItems().setAll(conversations);
    }


    public void folderByCategory(ActionEvent actionEvent) {
        String choice = (String) newMessageType.getValue();
        if(choice.equals("Speaking Event")){
            chooseNewFriend.setDisable(false);
            chooseNewFriend.getItems().setAll(mb.buildSpeakingEvent());
        }
        if(choice.equals("All Speakers") | choice.equals("All Users")){
            chooseNewFriend.setDisable(true);
        }
        if(choice.equals("Users")){
            chooseNewFriend.setDisable(false);
            buildNewMessage();
        }
    }
}
