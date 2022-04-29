package apClientUI.apOptions.Controllers;

import apClientUI.MainClientUIService;
import apClientUI.apAuthorization.AuthorizationServiceImp;
import apClientUI.apOptions.OptionsServiceImp;
import apCommon.ApFinals;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class OptionsController {
    @FXML
    private TextField textFieldIP;

    @FXML
    private TextField textFieldPort;

    public String GetServerIP(){
        return this.textFieldIP.getText();
    }

    public Integer GetServerPort(){
        return Integer.parseInt(this.textFieldPort.getText());
    }

    @FXML
    public void ConnectToServer(ActionEvent actionEvent) {
//        main.ConnectToServer();
    }
}
