package ap.Presentation.Controllers;

import ap.Domain.FacadeClientDomain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class OptionsControllerClientPresentation {

    @FXML
    private TextField textFieldIP;

    @FXML
    private TextField textFieldPort;

    public String GetIPFromTextField(){
        return this.textFieldIP.getText();
    }

    public Integer GetPortFromTextField(){
        return Integer.parseInt(this.textFieldPort.getText());
    }

    @FXML
    public void ConnectToServer(ActionEvent actionEvent) {
        FacadeClientDomain.ConnectToServer();
    }
}
