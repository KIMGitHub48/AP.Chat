package apClientUI.apAuthorization.Controllers;

import apClientUI.MainClientUIService;
import apClientUI.OptionsService;
import apClientUI.apAuthorization.AuthorizationButtonEnterInThread;
import apCommon.ApFinals;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

public class AuthorizationController {
    //private MainClientUI main = MainClientUI.mainClientUIRef;
    public static AuthorizationController authorizationController;
    public AuthorizationController (){
        authorizationController = this;
    };
    private OptionsService optionsService = OptionsService.getFirst();
    private MainClientUIService mainClientUIService = MainClientUIService.getFirst();

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
    }

    @FXML
    private void ButtonEnterAction(ActionEvent actionEvent) {
        ChangeButtonEnterTextAndDisable(ApFinals.ENTER_1, true);

        mainClientUIService.AuthorizationButtonEnterInThread();
        AuthorizationButtonEnterInThread authorizationButtonEnterInThread = new AuthorizationButtonEnterInThread();
        authorizationButtonEnterInThread.start();
    }

    public String GetLoginFromTextField(){
        String login = textFieldLogin.getText();
        return login;
    }

    public String GetPasswordFromTextField(){
        String password = textFieldPassword.getText();
        return password;
    }

    public String GetButtonEnterText(){
        return buttonEnter.getText();
    }

    public void ChangeButtonEnterTextAndDisable(String text, boolean disable) {
        Platform.runLater(() -> buttonEnter.setText(text));
        Platform.runLater(() -> buttonEnter.setDisable(disable));
    }


    @FXML
    private void ShowOptionsStage(ActionEvent actionEvent) {
        optionsService.ShowOptionsStage();
    }

    public void SetButtonEnterTooltipTextAndShow(String text) {
        Platform.runLater(() ->buttonEnter.getTooltip().setText(text));
        Platform.runLater(() ->buttonEnter.getTooltip().show(buttonEnter.getTooltip().getOwnerWindow()));
    }

    public void HideAuthorizationStage() {

    }
}
