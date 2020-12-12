package UI;

import UI.GeneralController;
import UI.Model;
import entities.Event;
import entities.Request;
import entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class RequestController extends GeneralController {

    @FXML
    protected Button newRequestButton;
    @FXML
    protected TableView requestTable;
    @FXML
    protected TableColumn<Request, String> requestColumn;
    @FXML
    protected TableColumn<Request,String> statusColumn;
    private ObservableList<Request> allRequests;
    private String fxmlName = "Requests.fxml";

    public RequestController() throws ClassNotFoundException {

    }
    @FXML
    public void initialize(){
        allRequests= FXCollections.observableArrayList();
        ArrayList<Request> reqs = mainModel.getRm().getRequestArrayList();
        allRequests.addAll(reqs);
        requestTable.setItems(allRequests);
        requestColumn.setCellValueFactory(cellData -> cellData.getValue().textString());
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusString());
    }
    public void newRequestButtonOnAction(javafx.event.ActionEvent actionEvent) throws IOException {
        System.out.println("NewButtonTest");
        newButtonStage("newRequest.fxml", newRequestButton);
        initialize();
    }


}
