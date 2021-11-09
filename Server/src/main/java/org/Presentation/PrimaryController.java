package org.Presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import static javafx.application.Platform.runLater;

public class PrimaryController {
    final String startServerButtonText;
    final private String stopServerButtonText;

    public PrimaryController(){
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
//        org.Domain.Net.Message chatMessage = new org.Domain.Net.Message();
//        chatMessage.setId("chatMessageToAll");
//        chatMessage.setText(textFieldMessage.getText());
//        org.Domain.Facade.SendMessage(chatMessage);
    }

    @FXML
    private void StartServer(ActionEvent actionEvent){
        if (buttonStartServer.getText().equals(startServerButtonText)){
            org.Domain1.Facade.StartServer();
        } else if (buttonStartServer.getText().equals(stopServerButtonText)){
            org.Domain1.Facade.StopServer();
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

}
