package ap.Presentation.Controllers;

import ap.Domain.Net.OutMessages.Authorization;
import ap.Presentation.MainClientPresentation;
import ap.common.ApFinals;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class LoginPasswordControllerClientPresentation {

    @FXML
    private TextField textFieldLogin;

    @FXML
    private TextField textFieldPassword;

    @FXML
    private Button buttonEnter;

    @FXML
    private Button buttonOptions;

    @FXML
    private Tooltip toolTipButtonEnter;

    @FXML
    public void initialize() {
//        Image image = new Image("OptionsPict.png");
//        Image image = new Image(getClass().getResource("OptionsPict.png").toExternalForm());
//        ImageView imageView = new ImageView(image);
//        buttonOptions.setGraphic(imageView);
    }

    @FXML
    private void Authorization(ActionEvent actionEvent){
        buttonEnter.setText(ApFinals.ENTER_1);
        buttonEnter.setDisable(true);
        if (ap.Domain.FacadeClientDomain.IsConnected()){
            ap.Domain.Net.OutMessages.Authorization authorization = new Authorization(textFieldLogin.getText(),textFieldPassword.getText());
            authorization.Send();
        } else {
            ConnectionWithAuthorizationAndTimer();
        }
    }

    private void ConnectionWithAuthorizationAndTimer(){
        ap.Domain.FacadeClientDomain.ConnectToServer(MainClientPresentation.mainPresentationRef.GetIPFromTextField(),MainClientPresentation.mainPresentationRef.GetPortFromTextField());
        Runnable runnable = () -> {
            boolean connectedFailFlag = true;
            for (int i=0;i<10;i++){
                System.out.println("Цикл таймера");
                try {
                    if (ap.Domain.FacadeClientDomain.IsConnected()){
                        ap.Domain.Net.OutMessages.Authorization authorization = new Authorization(textFieldLogin.getText(),textFieldPassword.getText());
                        authorization.Send();
                        Platform.runLater(() -> buttonEnter.setDisable(false));
                        Platform.runLater(() -> buttonEnter.setText(ApFinals.ENTER));
                        connectedFailFlag = false;
                        break;
                    } else {
                        System.out.println("Ожидаю");
                        TimeUnit.MILLISECONDS.sleep(500);
                        ChangeButtonEnterWaitingText(buttonEnter.getText());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (connectedFailFlag){
                Platform.runLater(() -> SetButtonEnterTooltipTextAndShow(ApFinals.SERVER_CONNECT_ERROR));
                Platform.runLater(() -> buttonEnter.setDisable(false));
                Platform.runLater(() -> buttonEnter.setText(ApFinals.ENTER));
            }
        };
        new Thread(runnable).start();
    }

    private void ChangeButtonEnterWaitingText(String buttonEnterText){
        switch (buttonEnter.getText()) {
            case (ApFinals.ENTER_1):
                Platform.runLater(() -> buttonEnter.setText(ApFinals.ENTER_2));
                break;
            case (ApFinals.ENTER_2):
                Platform.runLater(() -> buttonEnter.setText(ApFinals.ENTER_3));
                break;
            case (ApFinals.ENTER_3):
                Platform.runLater(() -> buttonEnter.setText(ApFinals.ENTER_1));
                break;
        }
    }

    @FXML
    private void OpenOptions(ActionEvent actionEvent){
        MainClientPresentation.mainPresentationRef.ShowOptionsStage();
    }

    public void SetButtonEnterTooltipTextAndShow(String text){
        buttonEnter.getTooltip().setText(text);
        buttonEnter.getTooltip().show(buttonEnter.getTooltip().getOwnerWindow());
    }
}
