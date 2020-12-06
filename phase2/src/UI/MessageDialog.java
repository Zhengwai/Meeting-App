package UI;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class MessageDialog {
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
}
