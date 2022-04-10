//package apClientCore.Presentation.Messages;
//
//import apClientCore.Core.MainClientCore;
//import apClientCore.Core.FacadeClientDomain;
//import apClientCore.Core.Net.InMessages.Authorization.AuthorizationResponseAction;
//import apClientCore.Presentation.MainClientPresentation;
//import apCommon.ApFinals;
//import apCommon.ApMessage;
//import apCommon.ApMessageEnumType;
//
//import java.util.UUID;
//import java.util.concurrent.TimeUnit;
//
//public class AuthorizationButtonEnterInThread extends Thread {
//
//    @Override
//    public void run() {
//        MainClientPresentation.mainPresentationRef.ChangeButtonEnterTextAndDisable(ApFinals.ENTER_1, true);
//        AuthorizationOrConnect();
//    }
//
//    private void AuthorizationOrConnect() {
//        if (FacadeClientDomain.IsConnected()) {
//            SendAuthorizationMessage();
//        } else {
//            if (ConnectWithTimer()){
//                SendAuthorizationMessage();
//            } else {
//                AuthorizationResponseAction authorizationResponseAction = new AuthorizationResponseAction();
//                authorizationResponseAction.ConnectionError();
//            }
//        }
//    }
//
//    private boolean ConnectWithTimer(){
//        MainClientCore.mainDomainRef.ConnectToServer();
//        for (int i = 0; i < 10; i++) {
//            if (MainClientCore.mainDomainRef.IsConnected()){
//                return true;
//            } else {
//                try {
//                    ChangeButtonEnterWaitingText();
//                    TimeUnit.MILLISECONDS.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return false;
//    }
//
//    private void ChangeButtonEnterWaitingText() {
//        switch (MainClientPresentation.mainPresentationRef.GetButtonEnterText()) {
//            case (ApFinals.ENTER_1):
//                MainClientPresentation.mainPresentationRef.ChangeLoginPasswordButtonEnter(ApFinals.ENTER_2, true);
//                break;
//            case (ApFinals.ENTER_2):
//                MainClientPresentation.mainPresentationRef.ChangeLoginPasswordButtonEnter(ApFinals.ENTER_3, true);
//                break;
//            case (ApFinals.ENTER_3):
//                MainClientPresentation.mainPresentationRef.ChangeLoginPasswordButtonEnter(ApFinals.ENTER_1, true);
//                break;
//        }
//    }
//
//    private void SendAuthorizationMessage() {
//        FacadeClientDomain.setAuthorizationAvailable(true);
//
//        ApMessage apMessage = new ApMessage();
//        apMessage = WriteDateInMessage(apMessage);
//        FacadeClientDomain.SortOutMessageInThread(apMessage);
//        FacadeClientDomain.WaitingAuthorizationResponse();
//    }
//
//    private ApMessage WriteDateInMessage(ApMessage apMessage) {
//        //TODO Не понятно тут надо вводить Логин пасс или нет?
//        apMessage.setType(ApMessageEnumType.authorization);
//        apMessage.setLogin(MainClientPresentation.mainPresentationRef.GetLoginFromTextField());
//        apMessage.setPassword(MainClientPresentation.mainPresentationRef.GetPasswordFromTextField());
//        apMessage.setUUID(UUID.randomUUID());
//        return apMessage;
//    }
//}
