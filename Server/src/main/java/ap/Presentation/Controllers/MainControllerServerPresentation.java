package ap.Presentation.Controllers;
import ap.Domain.FacadeServerDomain;
import apCommon.*;
import apCommon.ApMessage;
import apCommon.ApMessageEnumType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import static javafx.application.Platform.runLater;

public class MainControllerServerPresentation {
    final String startServerButtonText;
    final private String stopServerButtonText;

    public MainControllerServerPresentation(){
        startServerButtonText="Старт сервера";
        stopServerButtonText="Стоп сервера";
    }
    @FXML
    private Pane paneMainOptions;

    @FXML
    private TabPane TabPaneOptions;

    @FXML
    private AnchorPane tabPaneMain;

    @FXML
    private TextField textFieldIP;

    @FXML
    private TextField textFieldPort;

    @FXML
    private Button buttonStartServer;

    @FXML
    private TextArea textAreaDialog;

    @FXML
    private TextField textFieldMessage;

    @FXML
    private Button buttonSendMessage;

    @FXML
    public void initialize() {
        buttonStartServer.setText(startServerButtonText);
    }

    @FXML
    private void SendMessage(ActionEvent actionEvent){
        ApMessage chatMessage = new ApMessage();
        chatMessage.setType(ApMessageEnumType.chatChannelText);
        chatMessage.setChatChannelText(textFieldMessage.getText());
//        FacadeServerDomain.SendMessage(chatMessage);
    }

    @FXML
    private void StartServer(ActionEvent actionEvent){
        if (buttonStartServer.getText().equals(startServerButtonText)){
            FacadeServerDomain.StartServer();
        } else if (buttonStartServer.getText().equals(stopServerButtonText)){
            FacadeServerDomain.StopServer();
        }
    }

    public void SetSystemText(String text){
        textAreaDialog.appendText("Системное сообщение: "+text+"\n");
    }

    public void AddTextToStartServerButtonAfterStart(){
        Runnable addTextToStartServerButtonRunnable = () -> {
            buttonStartServer.setText(stopServerButtonText);
        };
        runLater(addTextToStartServerButtonRunnable);//Специальный метод для работы с объектами JavaFx из стороннего потока.
    }
    public void AddTextToStartServerButtonAfterStop(){
        Runnable addTextToStartServerButtonRunnable = () -> {
            buttonStartServer.setText(startServerButtonText);
        };
        runLater(addTextToStartServerButtonRunnable);//Специальный метод для работы с объектами JavaFx из стороннего потока.
    }

    public void ConnectToServer(ActionEvent actionEvent) {
    }
}
