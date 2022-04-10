//package apClientCore.Core.Net.InMessages.Authorization;
//
//import apClientCore.Core.MainClientCoreService;
//import apClientCore.Presentation.MainClientPresentation;
//import apCommon.ApFinals;
//
//import java.util.concurrent.TimeUnit;
//
//public class WaitingAuthorizationResponse {
//    private boolean timeFlag;
//    private MainClientCoreService mainClientCoreService = MainClientCoreService.getFirst();
//
//    public void waitResponse() {
//        for (int i = 0; i < 10; i++) {
//            if (mainClientCoreService.isAuthorizationAvailable()) {
//                try {
//                    TimeUnit.MILLISECONDS.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                ChangeButtonEnterWaitingText();
//            } else {
//                timeFlag = true;
//                break;
//            }
//        }
//        if (!timeFlag) {
//            AuthorizationResponseAction authorizationResponseAction = new AuthorizationResponseAction();
//            authorizationResponseAction.AuthorizationTimeError();
//        }
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
//}
