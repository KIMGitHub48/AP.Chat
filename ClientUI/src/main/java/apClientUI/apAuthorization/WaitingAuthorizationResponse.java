package apClientUI.apAuthorization;

import apClientUI.AuthorizationService;
import apClientUI.MainClientUIService;
import apCommon.ApFinals;

import java.util.concurrent.TimeUnit;

public class WaitingAuthorizationResponse {
    private boolean timeFlag;
    private MainClientUIService mainClientUIService = MainClientUIService.getFirst();
    private AuthorizationService authorizationService = AuthorizationService.getFirst();

    public void waitResponse() {
        for (int i = 0; i < 10; i++) {
            if (mainClientUIService.isAuthorizationAvailable()) {
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
            authorizationService.AuthorizationResponseActionTimeError();
        }
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
}
