//package apClientAuthorization.Messages.In;
//
//
//import apClientAuthorization.MainClientAuthorization;
//import apCommon.ApFinals;
//import apCommon.apModuleServices.ClientCoreService;
//
//public class AuthorizationResponseAction {
//    private MainClientAuthorization main = MainClientAuthorization.mainClientAuthorizationRef;
//    private ClientCoreService clientCoreService = apCommon.apModuleServices.ClientCoreService.getFirst();
//
//    public void ConnectionError(){
//        main.setAuthorizationAvailable(false);
//
//        main.SetButtonEnterTooltipTextAndShow(ApFinals.SERVER_CONNECT_ERROR);
//        main.ChangeButtonEnterTextAndDisable(ApFinals.ENTER, false);
//    }
//    public void AuthorizationError(){
//        main.setAuthorizationAvailable(false);
//
//        main.SetButtonEnterTooltipTextAndShow(ApFinals.AUTHORIZATION_NOT_PASSED);
//        main.ChangeButtonEnterTextAndDisable(ApFinals.ENTER, false);
//    }
//    public void AuthorizationPassed(String login, String Password){
//        main.setAuthorizationAvailable(false);
//
//        clientCoreService.setLogin(login);
//        clientCoreService.setPassword(Password);
//        //clientCoreService.ShowHideChatStage(true);
//        main.HideAuthorizationStage();
//        main.ChangeButtonEnterTextAndDisable(ApFinals.ENTER, false);
//    }
//    public void AuthorizationTimeError(){
//        main.setAuthorizationAvailable(false);
//
//        main.SetButtonEnterTooltipTextAndShow(ApFinals.SERVER_AUTHORIZATION_TIME_ERROR);
//        main.ChangeButtonEnterTextAndDisable(ApFinals.ENTER, false);
//    }
//}
