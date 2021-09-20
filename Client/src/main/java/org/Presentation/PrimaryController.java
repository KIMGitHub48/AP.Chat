package org.Presentation;

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
    private TextField textFieldMessage;

    @FXML
    private Button buttonSendMessage;

    @FXML
    private void SendMessage(){
        String textFromTextFieldMessage;
        textFromTextFieldMessage = textFieldMessage.getText();
        String returnText;
        returnText = org.Domain.Facade.SendMessage(textFromTextFieldMessage);
        textAreaDialog.appendText(returnText+"\n");
    }

}
