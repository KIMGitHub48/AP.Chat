package ap.Presentation.Controllers;

import ap.Domain.FacadeClientDomain;
import ap.common.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.util.UUID;

public class ChatControllerClientPresentation {

    @FXML
    private Pane panePrimary;

    @FXML
    private TextArea textAreaChatDialog;

    @FXML
    private TextArea textAreaNames;

    @FXML
    private TextField textFieldChannelMessage;

    @FXML
    private Button buttonSendMessage;

    @FXML
    private Button buttonConnect;

    @FXML
    private TextField textFieldIP;

    @FXML
    private TextField textFieldPort;

    @FXML
    private void SendMessage(ActionEvent actionEvent){
        //Тип сообщения
        ApMessage apMessage = new ApMessage();
        apMessage.setType(ApMessageEnumType.chatChannelText);
        apMessage.setUUID(UUID.randomUUID());
        apMessage.setChatChannelText(textFieldChannelMessage.getText());
        apMessage.setChatChannelName("Default");
        FacadeClientDomain.SendMessageToServer(apMessage);
    }

    @FXML
    public void ConnectToServer(ActionEvent actionEvent) {
        FacadeClientDomain.ConnectToServer();
    }

    public String GetIPFromTextField(){
        return this.textFieldIP.getText();
    }

    public Integer GetPortFromTextField(){
        return Integer.parseInt(this.textFieldPort.getText());
    }

    public void SetSystemText(String text){
        this.textAreaChatDialog.appendText("Системное сообщение: "+text+"\n");
    }

    public void setChatChannelMessage(String channelMessage, String channelName) {
        this.textAreaChatDialog.appendText(channelMessage+"\n");
    }
}
