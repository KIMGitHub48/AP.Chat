package apClientCore.Domain.Net.InMessages.Authorization;


import apClientCore.Domain.MainClientCoreService;
import apClientCore.Presentation.FacadeClientPresentation;
import apClientCore.Presentation.MainClientPresentation;
import apCommon.ApFinals;

public class AuthorizationResponseAction {
    private apCommon.apModuleServices.ClientUIService clientUIService = apCommon.apModuleServices.ClientUIService.getFirst();
    private MainClientCoreService mainClientCoreService = MainClientCoreService.getFirst();

    public void ConnectionError(){
        mainClientCoreService.setAuthorizationAvailable(false);

        FacadeClientPresentation.SetButtonEnterTooltipTextAndShow(ApFinals.SERVER_CONNECT_ERROR);
        MainClientPresentation.mainPresentationRef.ChangeLoginPasswordButtonEnter(ApFinals.ENTER, false);
    }
    public void AuthorizationIsNotPassed(){
        mainClientCoreService.setAuthorizationAvailable(false);

        FacadeClientPresentation.SetButtonEnterTooltipTextAndShow(ApFinals.AUTHORIZATION_ERROR);
        MainClientPresentation.mainPresentationRef.ChangeLoginPasswordButtonEnter(ApFinals.ENTER, false);
    }
    public void AuthorizationPassed(String login, String Password){
        mainClientCoreService.setAuthorizationAvailable(false);

        mainClientCoreService.setLogin(login);
        mainClientCoreService.setPassword(Password);
        MainClientPresentation.mainPresentationRef.ShowHideChatStage(true);
        MainClientPresentation.mainPresentationRef.ShowHideLoginPasswordStage(false);
        MainClientPresentation.mainPresentationRef.ChangeLoginPasswordButtonEnter(ApFinals.ENTER, false);
    }
    public void AuthorizationTimeError(){
        mainClientCoreService.setAuthorizationAvailable(false);

        FacadeClientPresentation.SetButtonEnterTooltipTextAndShow(ApFinals.SERVER_AUTHORIZATION_TIME_ERROR);
        MainClientPresentation.mainPresentationRef.ChangeLoginPasswordButtonEnter(ApFinals.ENTER, false);
    }
}
