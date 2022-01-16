package ap.Presentation.Controllers;

import ap.Domain.MainClientDomain;
import ap.Domain.Net.OutMessages.Authorization;
import ap.Presentation.MainClientPresentation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class LoginPasswordControllerClientPresentation {
    private String buttonEnterEnter = "Вход";
    private String buttonEnterEntering = "Вход...";
    private String buttonEnterError = "Ошибка";

    @FXML
    private TextField textFieldLogin;

    @FXML
    private TextField textFieldPassword;

    @FXML
    private Button buttonEnter;

    @FXML
    private Button buttonOptions;

    @FXML
    public void initialize() {
//        Image image = new Image("OptionsPict.png");
//        Image image = new Image(getClass().getResource("OptionsPict.png").toExternalForm());
//        ImageView imageView = new ImageView(image);
//        buttonOptions.setGraphic(imageView);
    }

    @FXML
    private void Authorization(ActionEvent actionEvent){
        buttonEnter.setText(buttonEnterEntering);
        buttonEnter.setDisable(true);
        if (ap.Domain.FacadeClientDomain.IsConnected()){
            ap.Domain.Net.OutMessages.Authorization authorization = new Authorization();
            authorization.Send();
        } else {
            ConnectionWithAuthorizationAndTimer();
        }
    }

    private void ConnectionWithAuthorizationAndTimer(){
        ap.Domain.FacadeClientDomain.ConnectToServer(MainClientPresentation.mainPresentationRef.GetIPFromTextField(),MainClientPresentation.mainPresentationRef.GetPortFromTextField());
        Runnable runnable = () -> {
            boolean connectedFailFlag = true;
            for (int i=5;i==0;i--){
                System.out.println("Цикл таймера");
                try {
                    if (ap.Domain.FacadeClientDomain.IsConnected()){
                        ap.Domain.Net.OutMessages.Authorization authorization = new Authorization();
                        authorization.Send();
                        buttonEnter.setDisable(false);
                        buttonEnter.setText(buttonEnterEnter);
                        connectedFailFlag = false;
                        break;
                    } else {
                        System.out.println("Ожидаю");
                        TimeUnit.SECONDS.sleep(1);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (connectedFailFlag){
//                buttonEnter.setDisable(false);
//                buttonEnter.setText("Ошибка входа");
            }
        };
        new Thread(runnable).start();
    }

    @FXML
    private void OpenOptions(ActionEvent actionEvent){
        MainClientPresentation.mainPresentationRef.ShowOptionsStage();
    }
}
