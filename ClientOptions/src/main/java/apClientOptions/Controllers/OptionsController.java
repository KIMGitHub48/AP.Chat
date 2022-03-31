package apClientOptions.Controllers;

import apClientOptions.MainClientOptions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class OptionsController {
    MainClientOptions main = MainClientOptions.mainClientOptionsRef;

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
        main.ConnectToServer();
    }
}
