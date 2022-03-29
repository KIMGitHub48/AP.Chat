package apClientAuthorization.Controllers;
import apCommon.apModuleServices.ClientCoreService;
import apClientAuthorization.AuthorizationButtonEnterInThread;
import apCommon.ApFinals;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

public class AuthorizationController {

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
    private void OpenOptions(ActionEvent actionEvent) {
        ClientCoreService.getFirst().ShowOptionsStage();
        //MainClientPresentation.mainPresentationRef.ShowHideOptionsStage(true);
    }

    public void SetButtonEnterTooltipTextAndShow(String text) {
        Platform.runLater(() ->buttonEnter.getTooltip().setText(text));
        Platform.runLater(() ->buttonEnter.getTooltip().show(buttonEnter.getTooltip().getOwnerWindow()));
    }
}
