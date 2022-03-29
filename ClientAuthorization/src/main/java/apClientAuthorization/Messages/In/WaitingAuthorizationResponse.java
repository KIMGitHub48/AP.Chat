package apClientAuthorization.Messages.In;

import apClientAuthorization.MainClientAuthorization;
import apCommon.ApFinals;
import apCommon.apModuleServices.ClientCoreService;

import java.util.concurrent.TimeUnit;

public class WaitingAuthorizationResponse {
    boolean timeFlag;
    private MainClientAuthorization main = MainClientAuthorization.mainClientAuthorizationRef;

    public void waitResponse() {
        for (int i = 0; i < 10; i++) {
            if (main.isAuthorizationAvailable()) {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ChangeButtonEnterWaitingText();
            } else {
                timeFlag = true;
                break;
            }
        }
        if (!timeFlag) {
            AuthorizationResponseAction authorizationResponseAction = new AuthorizationResponseAction();
            authorizationResponseAction.AuthorizationTimeError();
        }
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
}
