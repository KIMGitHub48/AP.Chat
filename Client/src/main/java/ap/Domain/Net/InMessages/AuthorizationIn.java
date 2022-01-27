package ap.Domain.Net.InMessages;

import ap.Domain.MainClientDomain;
import ap.Presentation.MainClientPresentation;
import ap.common.ApFinals;
import ap.common.ApMessage;

public class AuthorizationIn {
    private ApMessage apMessage;

    public AuthorizationIn(ApMessage apMessageLocal){
        apMessage = apMessageLocal;
    }

    public void Process(){
       boolean isAuthorizationAvailable =  MainClientPresentation.mainPresentationRef.GetIsAuthorizationAvailable();
        if (isAuthorizationAvailable){
            if (apMessage.getAuthorizationPassed()) {
                MainClientDomain.mainDomainRef.setLogin(apMessage.getLogin());
                MainClientDomain.mainDomainRef.setPassword(apMessage.getPassword());
                MainClientPresentation.mainPresentationRef.setAuthorizationAvailable(false);
                MainClientPresentation.mainPresentationRef.ShowHideChatStage(true);
                MainClientPresentation.mainPresentationRef.ShowHideLoginPasswordStage(false);
                MainClientPresentation.mainPresentationRef.ChangeLoginPasswordButtonEnter(ApFinals.ENTER, true);
            } else {
                MainClientPresentation.mainPresentationRef.SetButtonEnterTooltipTextAndShow(ApFinals.AUTHORIZATION_ERROR);
                MainClientPresentation.mainPresentationRef.setAuthorizationAvailable(false);
            }
        }
    }
}
