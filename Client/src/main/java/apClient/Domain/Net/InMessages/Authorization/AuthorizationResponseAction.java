package apClient.Domain.Net.InMessages.Authorization;


import apClient.Domain.MainClientDomain;
import apClient.Presentation.FacadeClientPresentation;
import apClient.Presentation.MainClientPresentation;
import apCommon.ApFinals;

public class AuthorizationResponseAction {

    public void ConnectionError(){
        MainClientDomain.mainDomainRef.setAuthorizationAvailable(false);

        FacadeClientPresentation.SetButtonEnterTooltipTextAndShow(ApFinals.SERVER_CONNECT_ERROR);
        MainClientPresentation.mainPresentationRef.ChangeLoginPasswordButtonEnter(ApFinals.ENTER, false);
    }
    public void AuthorizationError(){
        MainClientDomain.mainDomainRef.setAuthorizationAvailable(false);

        FacadeClientPresentation.SetButtonEnterTooltipTextAndShow(ApFinals.AUTHORIZATION_ERROR);
        MainClientPresentation.mainPresentationRef.ChangeLoginPasswordButtonEnter(ApFinals.ENTER, false);
    }
    public void AuthorizationPassed(String login, String Password){
        MainClientDomain.mainDomainRef.setAuthorizationAvailable(false);

        MainClientDomain.mainDomainRef.setLogin(login);
        MainClientDomain.mainDomainRef.setPassword(Password);
        MainClientPresentation.mainPresentationRef.ShowHideChatStage(true);
        MainClientPresentation.mainPresentationRef.ShowHideLoginPasswordStage(false);
        MainClientPresentation.mainPresentationRef.ChangeLoginPasswordButtonEnter(ApFinals.ENTER, false);
    }
    public void AuthorizationTimeError(){
        MainClientDomain.mainDomainRef.setAuthorizationAvailable(false);

        FacadeClientPresentation.SetButtonEnterTooltipTextAndShow(ApFinals.SERVER_AUTHORIZATION_TIME_ERROR);
        MainClientPresentation.mainPresentationRef.ChangeLoginPasswordButtonEnter(ApFinals.ENTER, false);
    }
}
