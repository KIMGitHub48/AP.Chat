package apClientAuthorization;

//import apClient.Domain.FacadeClientDomain;
//import apClient.Domain.MainClientDomain;
//import apClient.Domain.Net.InMessages.Authorization.AuthorizationResponseAction;
//import apClient.Presentation.MainClientPresentation;
import apClientAuthorization.Messages.In.AuthorizationResponseAction;
import apCommon.ApFinals;
import apCommon.ApMessage;
import apCommon.ApMessageEnumType;
import apCommon.apModuleServices.ClientCoreService;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class AuthorizationButtonEnterInThread extends Thread {
    private MainClientAuthorization main = MainClientAuthorization.mainClientAuthorizationRef;
    private ClientCoreService clientCoreService = apCommon.apModuleServices.ClientCoreService.getFirst();

    @Override
    public void run() {
        main.ChangeButtonEnterTextAndDisable(ApFinals.ENTER_1, true);
        AuthorizationOrConnect();
    }

    private void AuthorizationOrConnect() {
        if (main.IsConnected()) {
            SendAuthorizationMessage();
        } else {
            if (ConnectWithTimer()){
                SendAuthorizationMessage();
            } else {
                AuthorizationResponseAction authorizationResponseAction = new AuthorizationResponseAction();
                authorizationResponseAction.ConnectionError();
            }
        }
    }

    private boolean ConnectWithTimer(){
        main.ConnectToServer();
        for (int i = 0; i < 10; i++) {
            if (main.IsConnected()){
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
        switch (main.GetButtonEnterText()) {
            case (ApFinals.ENTER_1):
                main.ChangeAuthorizationButtonEnterTextAndDisable(ApFinals.ENTER_2, true);
                break;
            case (ApFinals.ENTER_2):
                main.ChangeAuthorizationButtonEnterTextAndDisable(ApFinals.ENTER_3, true);
                break;
            case (ApFinals.ENTER_3):
                main.ChangeAuthorizationButtonEnterTextAndDisable(ApFinals.ENTER_1, true);
                break;
        }
    }

    private void SendAuthorizationMessage() {
        main.setAuthorizationAvailable(true);

        ApMessage apMessage = new ApMessage();
        apMessage = WriteDateInMessage(apMessage);
        clientCoreService.SortOutMessageInThread(apMessage);
        main.WaitingAuthorizationResponse();
    }

    private ApMessage WriteDateInMessage(ApMessage apMessage) {
        apMessage.setType(ApMessageEnumType.authorization);
        apMessage.setLogin(main.GetLoginFromTextField());
        apMessage.setPassword(main.GetPasswordFromTextField());
        apMessage.setUUID(UUID.randomUUID());
        return apMessage;
    }
}
