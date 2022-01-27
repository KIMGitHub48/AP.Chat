package ap.Presentation.Controllers;

import ap.Domain.MainClientDomain;
import ap.Domain.Net.OutMessages.AuthorizationOut;
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
            String login = textFieldLogin.getText();
            String password = textFieldPassword.getText();
            ap.Domain.FacadeClientDomain.SendAuthorizationMessage(login, password);
//            AuthorizationOut authorizationOut = new AuthorizationOut(login,password);
//            authorizationOut.Send();
        } else {
            ConnectionWithAuthorizationAndTimer();
        }
    }

    //TODO доработать цикл проверки сообщений

    private void ConnectionWithAuthorizationAndTimer(){
        String login = textFieldLogin.getText();
        String password = textFieldPassword.getText();
        String IP = MainClientPresentation.mainPresentationRef.GetIPFromTextField();
        Integer Port = MainClientPresentation.mainPresentationRef.GetPortFromTextField();
        ap.Domain.FacadeClientDomain.ConnectToServer(IP,Port);
        Runnable runnable = () -> {
            boolean connectedFailFlag = true;
            for (int i=0;i<10;i++){
                System.out.println("Цикл таймера");
                try {
                    if (ap.Domain.FacadeClientDomain.IsConnected()){
                        ap.Domain.FacadeClientDomain.SendAuthorizationMessage(login, password);
//                        AuthorizationOut authorizationOut = new AuthorizationOut(login,password);
//                        authorizationOut.Send();
                        Platform.runLater(() -> buttonEnter.setDisable(false));
                        Platform.runLater(() -> buttonEnter.setText(ApFinals.ENTER));
                        connectedFailFlag = false;
                        break;
                    } else {
                        System.out.println("Ожидаю");
                        TimeUnit.MILLISECONDS.sleep(500);
                        //ChangeLoginPasswordButtonEnter(buttonEnter.getText());
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

    public void ChangeLoginPasswordButtonEnter(String text, boolean disable){
        Platform.runLater(() -> buttonEnter.setText(text));
        Platform.runLater(() -> buttonEnter.setDisable(disable));
    }


//    private void ChangeButtonEnterWaitingText(String buttonEnterText){
//        switch (buttonEnter.getText()) {
//            case (ApFinals.ENTER_1):
//                Platform.runLater(() -> buttonEnter.setText(ApFinals.ENTER_2));
//                break;
//            case (ApFinals.ENTER_2):
//                Platform.runLater(() -> buttonEnter.setText(ApFinals.ENTER_3));
//                break;
//            case (ApFinals.ENTER_3):
//                Platform.runLater(() -> buttonEnter.setText(ApFinals.ENTER_1));
//                break;
//        }
//    }

    @FXML
    private void OpenOptions(ActionEvent actionEvent){
        MainClientPresentation.mainPresentationRef.ShowHideOptionsStage(true);
    }

    public void SetButtonEnterTooltipTextAndShow(String text){
        buttonEnter.getTooltip().setText(text);
        buttonEnter.getTooltip().show(buttonEnter.getTooltip().getOwnerWindow());
    }
}
