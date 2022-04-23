package apClientUI.apAuthorization;

//import apClient.Domain.FacadeClientDomain;
//import apClient.Domain.MainClientDomain;
//import apClient.Domain.Net.InMessages.Authorization.AuthorizationResponseAction;
//import apClient.Presentation.MainClientPresentation;
import apClientUI.AuthorizationService;
import apClientUI.MainClientUIService;
import apClientUI.OptionsService;
import apCommon.ApFinals;
import apCommon.ApMessage;
import apCommon.ApMessageEnumType;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class AuthorizationButtonEnterInThread extends Thread {
    //private MainClientAuthorization main = MainClientAuthorization.mainClientAuthorizationRef;
    private AuthorizationService authorizationService = AuthorizationService.getFirst();
    private MainClientUIService mainClientUIService = MainClientUIService.getFirst();
    private OptionsService optionsService = OptionsService.getFirst();

    @Override
    public void run() {
        authorizationService.ChangeAuthorizationButtonEnterTextAndDisable(ApFinals.ENTER_1, true);
        AuthorizationOrConnect();
    }

    private void AuthorizationOrConnect() {
        if (mainClientUIService.IsConnected()) {
            SendAuthorizationMessage();
        } else {
            if (ConnectWithTimer()){
                SendAuthorizationMessage();
            } else {
                authorizationService.AuthorizationResponseActionConnectionError();
            }
        }
    }

    private boolean ConnectWithTimer(){
        mainClientUIService.ConnectToServer();
        for (int i = 0; i < 10; i++) {
            if (mainClientUIService.IsConnected()){
                return true;
            } else {
                try {
                    ChangeButtonEnterWaitingText();
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    private void ChangeButtonEnterWaitingText() {
        switch (authorizationService.GetButtonEnterText()) {
            case (ApFinals.ENTER_1):
                authorizationService.ChangeAuthorizationButtonEnterTextAndDisable(ApFinals.ENTER_2, true);
                break;
            case (ApFinals.ENTER_2):
                authorizationService.ChangeAuthorizationButtonEnterTextAndDisable(ApFinals.ENTER_3, true);
                break;
            case (ApFinals.ENTER_3):
                authorizationService.ChangeAuthorizationButtonEnterTextAndDisable(ApFinals.ENTER_1, true);
                break;
        }
    }

    private void SendAuthorizationMessage() {
        mainClientUIService.setAuthorizationAvailable(true);

        ApMessage apMessage = new ApMessage();
        apMessage = WriteDateInMessage(apMessage);
        mainClientUIService.SendMessage(apMessage);
        WaitingAuthorizationResponse waitingAuthorizationResponse = new WaitingAuthorizationResponse();
        waitingAuthorizationResponse.waitResponse();
    }

    private ApMessage WriteDateInMessage(ApMessage apMessage) {
        apMessage.setType(ApMessageEnumType.authorization);
        apMessage.setLogin(authorizationService.GetLoginFromTextField());
        apMessage.setPassword(authorizationService.GetPasswordFromTextField());
        apMessage.setUUID(UUID.randomUUID());
        return apMessage;
    }
}
