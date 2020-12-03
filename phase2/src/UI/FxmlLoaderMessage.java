package UI;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class FxmlLoaderMessage {
    private Pane view;

    public Pane getPage(String fileName) {

        try {
            URL fileURL = Main.class.getResource(fileName);
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

