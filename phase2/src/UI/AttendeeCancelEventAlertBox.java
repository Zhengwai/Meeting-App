package UI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicReference;

public class AttendeeCancelEventAlertBox {

    public static Boolean display(){
        AtomicReference<Boolean> temp = new AtomicReference<>(true);
        Stage window = new Stage();
        window.setTitle("Sign Up for this Event?");
        window.setWidth(300);
        window.setHeight(300);

        Label label = new Label();
        label.setText("Are you sure you want to quit the event?");

        Button confirmButton = new Button();
        confirmButton.setText("Confirm!");
        confirmButton.setOnAction(e -> {temp.set(true);
            window.close();});

        Button denyButton = new Button();
        denyButton.setText("Not Now~");
        denyButton.setOnAction(e -> {temp.set(false);
            window.close();});

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, confirmButton, denyButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);

        window.showAndWait();
        return temp.get();

    }
}

