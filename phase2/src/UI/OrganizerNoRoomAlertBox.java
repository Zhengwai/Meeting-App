package UI;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class OrganizerNoRoomAlertBox {
    public static void display(){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Cancel Event");
        window.setWidth(300);
        window.setHeight(300);

        Label label = new Label();
        label.setText("There's no rooms yet! Please create a new room first.");
        label.setLayoutY(150);


        VBox layout = new VBox(10);
        layout.getChildren().addAll(label);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);

        window.showAndWait();
    }
}
