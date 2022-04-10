//package apClientCore.Presentation.Controllers;
//
//import apClientCore.Presentation.MainClientPresentation;
//import apClientCore.Presentation.Messages.ChatChannelSendButtonInThread;
//import javafx.application.Platform;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.control.TextArea;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.Pane;
//
//public class ChatControllerClientPresentation {
//
//    @FXML
//    private Pane panePrimary;
//
//    @FXML
//    private TextArea textAreaChatDialog;
//
//    @FXML
//    private TextArea textAreaNames;
//
//    @FXML
//    private TextField textFieldChannelMessage;
//
//    @FXML
//    private Button buttonSendMessage;
//
//    @FXML
//    private Button buttonConnect;
//
//    @FXML
//    private TextField textFieldIP;
//
//    @FXML
//    private TextField textFieldPort;
//
//    @FXML
//    private void SendMessageButtonAction(ActionEvent actionEvent){
//        ChatChannelSendButtonInThread chatChannelSendButtonInThread = new ChatChannelSendButtonInThread();
//        chatChannelSendButtonInThread.start();
//    }
//
//    @FXML
//    public void OptionsButtonAction(ActionEvent actionEvent) {
//        MainClientPresentation.mainPresentationRef.ShowHideOptionsStage(true);
//    }
//
//    public void SetSystemText(String text){
//        this.textAreaChatDialog.appendText("Системное сообщение: "+text+"\n");
//    }
//
//    public void setChatChannelMessage(String channelMessage, String channelName) {
//        Platform.runLater(() ->this.textAreaChatDialog.appendText(channelMessage+"\n"));
//    }
//
//    public String GetTextFromChatMessageTextField(){
//        return textFieldChannelMessage.getText();
//    }
//}
