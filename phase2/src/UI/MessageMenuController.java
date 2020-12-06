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

    ContextMenu contextMenu;

    SendeeHolder sendee = SendeeHolder.getInstance();

    ObservableList<String> notFriends;

    List<String> filterOptions = new ArrayList<String>(
            Arrays.asList("All Messages", "Unread Messages", "Archived Messages"));

    private ObservableList<String> friends;

    public MessageMenuController() throws ClassNotFoundException {
    }

    public void handleNewMessage(ActionEvent actionEvent) throws IOException {
        buildNewMessage();
        messageMain.setCenter(subPane);
        messageHistory.getItems().clear();
        subPane.setVisible(true);
    }

    public void buildNewMessage() {
        notFriends = FXCollections.observableList(
                mainModel.getUm().getAllNonFriendNames(mainModel.getCurrentUser().getID()));
        chooseNewFriend.getItems().setAll(notFriends);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buildContextMenu();
        filterMessages.getItems().setAll(filterOptions);
        System.out.println(mainModel.getCurrentUser().getUsername());
        UUID user = mainModel.getCurrentUser().getID();
        friends = FXCollections.observableList(mainModel.getUm().getAllFriendNames(user));
        if (friends.size() > 0) {
            myMessageList.getItems().setAll(friends);
        } else {
            myMessageList.setPlaceholder(new Label("No Messages"));
        }
        subPane.setVisible(false);
    }

    public void buildContextMenu(){
        contextMenu = new ContextMenu();
        MenuItem archive = new MenuItem("Archive Message");
        MenuItem delete = new MenuItem("Delete Message");
        MenuItem unread = new MenuItem("Set Unread");

        archive.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Archive");
                MessageDialog.display("archive");
            }
        });

        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Delete");
                MessageDialog.display("delete");
            }
        });

        unread.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Unread");
            }
        });

        contextMenu.getItems().add(archive);
        contextMenu.getItems().add(delete);
        contextMenu.getItems().add(unread);

        myMessageList.setContextMenu(contextMenu);
    }

    public void handleSelectChat(MouseEvent mouseEvent) {
        if(mouseEvent.getButton() == MouseButton.SECONDARY){
            String recipienttest = (String) myMessageList.getSelectionModel().getSelectedItem();
            System.out.println("Right click :)" + recipienttest);
            contextMenu = new ContextMenu();
        } else {
            String recipient = (String) myMessageList.getSelectionModel().getSelectedItem();
            sendee.setSendee(mainModel.getUm().getUserByName(recipient).getID());
            FxmlLoaderMessage object = new FxmlLoaderMessage();
            Pane view = object.getPage("Chat.fxml");
            messageMain.setCenter(view);
            System.out.println(sendee);
        }
    }

    public void handleSendNewAction(ActionEvent actionEvent) {

        String newFriend = (String) chooseNewFriend.getValue();
        System.out.println(newFriend);
        String myMessage = message.getText();
        messageHistory.getItems().setAll("Me: " + myMessage);
        mainModel.getUm().addFriends(mainModel.getCurrentUser().getID(),
                mainModel.getUm().getUserByName(newFriend).getID());

        UUID conID = mainModel.getCm().newConversation();
        mainModel.getCm().addUserToConversation(conID, mainModel.getCurrentUser().getID());
        mainModel.getCm().addUserToConversation(conID, mainModel.getUm().getUserByName(newFriend).getID());

        this.message.setText("");

        updateLists(newFriend);
    }

    public void updateLists(String friend){
        friends.add(friend);
        notFriends.remove(friend);
        chooseNewFriend.getItems().setAll(notFriends);
        myMessageList.getItems().setAll(friends);
    }
}
