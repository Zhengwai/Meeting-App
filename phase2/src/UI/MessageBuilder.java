package UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MessageBuilder {

    public ContextMenu buildContextMenu(){
        ContextMenu contextMenu = new ContextMenu();
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

        return contextMenu;
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
}
