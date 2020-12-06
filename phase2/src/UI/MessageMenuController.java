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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

public class MessageMenuController extends GeneralController implements Initializable{

    @FXML
    Button newMessageButton;
    @FXML
    BorderPane messageMain;
    @FXML
    ComboBox chooseNewFriend;
    @FXML
    ListView myMessageList;
    @FXML
    BorderPane subPane;

    SendeeHolder sendee = SendeeHolder.getInstance();

    private ObservableList<String>  friends = FXCollections.observableArrayList("Jenn","Joe","Sherry", "Eunice");

    public MessageMenuController() throws ClassNotFoundException {
    }

    public void buildNewMessageSurroundings(){
        Label toLabel = new Label("To: ");

        ComboBox<String> newFriends = new ComboBox<String>();

        newFriends.getItems().setAll(mainModel.getUm().getAllNonFriendNames(mainModel.getCurrentUser().getID()));

        HBox hbox = new HBox(toLabel, newFriends);

        subPane.setTop(hbox);

        TextField messageField = new TextField();
        messageField.setPromptText("Enter your message");

        Button send = new Button("Send");

        HBox bottom = new HBox(messageField, send);

        subPane.setBottom(bottom);
    }

    public void buildMessageUserSurroundings(){

    }

    public void handleNewMessage(ActionEvent actionEvent) throws IOException {
        //buildNewMessageSurroundings();
        FxmlLoaderMessage object = new FxmlLoaderMessage();
        Pane view = object.getPage("NewMessage.fxml");
        messageMain.setCenter(view);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //System.out.println(mainModel);
        System.out.println(mainModel.getCurrentUser().getUsername());
        UUID user = mainModel.getCurrentUser().getID();
        List<String> friends = mainModel.getUm().getAllFriendNames(user);
        if(friends.size() > 0){
            myMessageList.getItems().setAll(friends);
        } else{
            myMessageList.setPlaceholder(new Label("No Messages"));
        }
    }

    public void handleSelectChat(MouseEvent mouseEvent) {
        String recipient = (String) myMessageList.getSelectionModel().getSelectedItem();
        sendee.setSendee(mainModel.getUm().getUserByName(recipient).getID());
        FxmlLoaderMessage object = new FxmlLoaderMessage();
        Pane view = object.getPage("Chat.fxml");
        messageMain.setCenter(view);
        System.out.println(sendee);

    }
}
