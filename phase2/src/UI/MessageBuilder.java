package UI;

import controllers.MessageControllerAdapter;
import entities.Event;
import entities.Speaker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class MessageBuilder {
    Model mainModel = new Model();
    MessageControllerAdapter mca = new MessageControllerAdapter(mainModel.getCurrentUser().getID(), mainModel.getCm(), mainModel.getUm(), mainModel.getEm());
    ConversationHolder ch = ConversationHolder.getInstance();

    public MessageBuilder() throws ClassNotFoundException {
    }

    public Pane chatBuilder(){
        Pane view = new Pane();
        try {
            URL fileURL = Main.class.getResource("Chat.fxml");
            if (fileURL == null) {
                throw new java.io.FileNotFoundException("FXML File could not be found");
            }
            view = new FXMLLoader().load(fileURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    /*
    public List<String> buildMyConversations(List<UUID> conversations){
        List<String> output = new ArrayList<>();

        for (int i = 0; i < conversations.size(); i++) {
            String name = "";

            if (mainModel.getCm().noNameExists(conversations.get(i))) {
                ArrayList<UUID> memberIds = mainModel.getCm().getMemberIDsInConversation(conversations.get(i));

                for (UUID memberID : memberIds) {
                    if (!memberID.equals(mainModel.getCurrentUser().getID())) {
                        name = mainModel.getUm().getUserByID(memberID).getUsername();
                    }
                }

            } else {
                name = mainModel.getCm().getName(conversations.get(i));
            }

            output.add(name);
        }

        return output;
    }
    */

    public List<String> buildChatContents(ArrayList<UUID> msgIDs) {
        StringBuilder output = new StringBuilder();
        List<String> messages = new ArrayList<>();

        for (String[] msg : mainModel.getCm().getMessagesInList(msgIDs)) {
            String name = mainModel.getUm().getUserByID(UUID.fromString(msg[0])).getUsername();
            output.append(name).append(" ").append(msg[1]).append(": ").append(msg[2]).append("\n");
            String thisMessage = output.toString();
            messages.add(thisMessage);

        }

        return messages;
    }

    public List<String> buildSpeakingEvent(){
        List<String> mySpeakingEvents;

        if(mainModel.getCurrentUser().getType().equals("s")){
            mySpeakingEvents = mainModel.getEm().getEventNamesBySpeaker((Speaker) mainModel.getCurrentUser());
            return mySpeakingEvents;
        } else if (mainModel.getCurrentUser().getType().equals("o")){
            mySpeakingEvents = mainModel.getEm().getAllEventNames();
            return mySpeakingEvents;
        }

        mySpeakingEvents = new ArrayList<>();
        return mySpeakingEvents;
    }

    /*
    public static void display(String a){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(a + " Message");
        window.setMinWidth(400);

        Label label = new Label();
        label.setText("Are you sure that you want to " + a + " this message?");
        Button confirmButton = new Button("Yes");
        Button denyButton = new Button("No");
        HBox hbox = new HBox(10, confirmButton, denyButton);
        hbox.setAlignment(Pos.CENTER);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, hbox);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);

        window.showAndWait();
    }
     */
}
