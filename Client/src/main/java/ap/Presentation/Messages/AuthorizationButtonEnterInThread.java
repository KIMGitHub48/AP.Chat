package ap.Presentation.Messages;

import ap.Domain.MainClientDomain;
import ap.Domain.Net.InMessages.Authorization.AuthorizationResponseAction;
import ap.Presentation.MainClientPresentation;
import ap.common.ApFinals;
import ap.common.ApMessage;
import ap.common.ApMessageEnumType;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class AuthorizationButtonEnterInThread extends Thread {

    @Override
    public void run() {
        MainClientPresentation.mainPresentationRef.ChangeButtonEnterTextAndDisable(ApFinals.ENTER_1, true);
        AuthorizationOrConnect();
    }

    private void AuthorizationOrConnect() {
        if (ap.Domain.FacadeClientDomain.IsConnected()) {
            SendAuthorizationMessage();
        } else {
            if (ConnectWithTimer()){
                SendAuthorizationMessage();
            } else {
                ap.Domain.Net.InMessages.Authorization.AuthorizationResponseAction authorizationResponseAction = new AuthorizationResponseAction();
                authorizationResponseAction.ConnectionError();
            }
        }
    }

    private boolean ConnectWithTimer(){
        MainClientDomain.mainDomainRef.ConnectToServer();
        for (int i = 0; i < 10; i++) {
            if (MainClientDomain.mainDomainRef.IsConnected()){
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
        switch (MainClientPresentation.mainPresentationRef.GetButtonEnterText()) {
            case (ApFinals.ENTER_1):
                MainClientPresentation.mainPresentationRef.ChangeLoginPasswordButtonEnter(ApFinals.ENTER_2, true);
                break;
            case (ApFinals.ENTER_2):
                MainClientPresentation.mainPresentationRef.ChangeLoginPasswordButtonEnter(ApFinals.ENTER_3, true);
                break;
            case (ApFinals.ENTER_3):
                MainClientPresentation.mainPresentationRef.ChangeLoginPasswordButtonEnter(ApFinals.ENTER_1, true);
                break;
        }
    }

    private void SendAuthorizationMessage() {
        ap.Domain.FacadeClientDomain.setAuthorizationAvailable(true);

        ApMessage apMessage = new ApMessage();
        apMessage = WriteDateInMessage(apMessage);
        ap.Domain.FacadeClientDomain.SortOutMessageInThread(apMessage);
        ap.Domain.FacadeClientDomain.WaitingAuthorizationResponse();
    }

    private ApMessage WriteDateInMessage(ApMessage apMessage) {
        //TODO Не понятно тут надо вводить Логин пасс или нет?
        apMessage.setType(ApMessageEnumType.authorization);
        apMessage.setLogin(MainClientPresentation.mainPresentationRef.GetLoginFromTextField());
        apMessage.setPassword(MainClientPresentation.mainPresentationRef.GetPasswordFromTextField());
        apMessage.setUUID(UUID.randomUUID());
        return apMessage;
    }
}
