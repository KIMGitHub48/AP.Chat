package apClientCore.Core.Net.InMessages.Authorization;


import apClientCore.Core.MainClientCoreService;
//import apClientCore.Presentation.FacadeClientPresentation;
//import apClientCore.Presentation.MainClientPresentation;
import apCommon.apModuleServices.ClientService.ClientUIService;

public class AuthorizationResponseAction {
    private ClientUIService clientUIService = ClientUIService.getFirst();
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
        mainClientCoreService.SetLogin(login);
        mainClientCoreService.SetPassword(Password);
        clientUIService.AuthorizationResponseActionPassed();
    }
}
