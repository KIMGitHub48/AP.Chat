package org.Presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class PrimaryController {

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
        org.Domain.Net.Message message = new org.Domain.Net.Message();
        message.setId("chatChannelText");
        message.setChatChannelText(textFieldChannelMessage.getText());
        message.setChatChannelName("Default");
        org.Domain.Facade.SendMessageToServer(message);
    }

    @FXML
    public void ConnectToServer(ActionEvent actionEvent) {
        org.Domain.Facade.ConnectToServer();
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
