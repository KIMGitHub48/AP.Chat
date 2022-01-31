package ap.Presentation.Controllers;

import ap.Domain.FacadeServerDomain;
import ap.common.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import static javafx.application.Platform.runLater;

public class ChatControllerServerPresentation {
    final String startServerButtonText;
    final private String stopServerButtonText;

    public ChatControllerServerPresentation(){
        startServerButtonText="Старт сервера";
        stopServerButtonText="Стоп сервера";
    }

    @FXML
    private Pane panePrimary;

    @FXML
    private TextArea textAreaDialog;

    @FXML
    private TextArea textAreaNames;

    @FXML
    private TextField textFieldMessage;

    @FXML
    private Button buttonSendMessage;

    @FXML
    private Button buttonStartServer;

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
