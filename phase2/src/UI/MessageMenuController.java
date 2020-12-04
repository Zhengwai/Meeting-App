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

    private ObservableList<String>  friends = FXCollections.observableArrayList("Jenn","Joe","Sherry", "Eunice");

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
        buildNewMessageSurroundings();
        //FxmlLoaderMessage object = new FxmlLoaderMessage();
        //Pane view = object.getPage("NewMessage.fxml");
        //messageMain.setCenter(view);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //System.out.println(mainModel);
        //System.out.println(mainModel.getCurrentUser().getUsername());
        //String user = mainModel.getCurrentUser().getUsername();
        //List<String> friends = mainModel.getUm().getAllFriendNames(user);
        //ObservableList<String>  friends = FXCollections.observableArrayList("Jenn","Joe","Sherry", "Eunice");
        //List<String> friendshaha = new ArrayList<>();
        //friendshaha.add(user);
        /*
        if(friendshaha.size() > 0) {
            for (String friend : friendshaha) {
                myMessageList.getItems().add(friend);
            }
        }
        else{
            myMessageList.setPlaceholder(new Label("No Content In List"));
        }
        //myMessageList.getItems().add("a");
        //myMessageList.setItems(myFriends);
        )
         */

        myMessageList.setPlaceholder(new Label("No Messages"));
    }
}
