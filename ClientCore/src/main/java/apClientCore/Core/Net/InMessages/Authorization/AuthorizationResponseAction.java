package apClientCore.Core.Net.InMessages.Authorization;


import apClientCore.Core.MainClientCoreService;
//import apClientCore.Presentation.FacadeClientPresentation;
//import apClientCore.Presentation.MainClientPresentation;
import apCommon.ApFinals;

public class AuthorizationResponseAction {
    private apCommon.apModuleServices.ClientUIService clientUIService = apCommon.apModuleServices.ClientUIService.getFirst();
    private MainClientCoreService mainClientCoreService = MainClientCoreService.getFirst();

    public void AuthorizationResponseActionConnectionError(){
        mainClientCoreService.setAuthorizationAvailable(false);
        clientUIService.AuthorizationResponseActionConnectionError();
    }
    public void AuthorizationResponseActionNotPassed(){
        mainClientCoreService.setAuthorizationAvailable(false);
        clientUIService.AuthorizationResponseActionNotPassed();
    }
    public void AuthorizationResponseActionPassed(String login, String Password){
        mainClientCoreService.setAuthorizationAvailable(false);
        mainClientCoreService.setLogin(login);
        mainClientCoreService.setPassword(Password);
        clientUIService.AuthorizationResponseActionPassed();
    }
//    public void AuthorizationTimeError(){
//        mainClientCoreService.setAuthorizationAvailable(false);
//
//        FacadeClientPresentation.SetButtonEnterTooltipTextAndShow(ApFinals.SERVER_AUTHORIZATION_TIME_ERROR);
//        MainClientPresentation.mainPresentationRef.ChangeLoginPasswordButtonEnter(ApFinals.ENTER, false);
//    }
}
