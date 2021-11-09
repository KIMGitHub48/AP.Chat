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
    private TextArea textAreaDialog;

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
    private void SendMessage(){
        //Тип сообщения
        org.Domain1.Net.Message message = new org.Domain1.Net.Message();
        message.setId("chatMessageToAll");
        message.setChatChannelText(textFieldChannelMessage.getText());
        message.setChatChannelName("Default");
        org.Domain1.Facade.SendMessage(message);
    }

    public void connectToServer(ActionEvent actionEvent) {
        org.Domain1.Facade.ConnectToServer();
    }

    public String GetIPFromTextField(){
        return this.textFieldIP.getText();
    }

    public Integer GetPortFromTextField(){
        return Integer.parseInt(this.textFieldPort.getText());
    }

    public void SetSystemText(String text){
        this.textAreaDialog.appendText("Системное сообщение: "+text+"\n");
    }

    public void setTextFieldChannelMessage(String channelMessage, String channelName) {
        this.textFieldChannelMessage.appendText(channelMessage+"\n");
    }
}
