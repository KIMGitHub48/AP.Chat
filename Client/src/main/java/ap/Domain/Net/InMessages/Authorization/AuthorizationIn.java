package ap.Domain.Net.InMessages.Authorization;

import ap.Domain.MainClientDomain;
import ap.Presentation.MainClientPresentation;
import ap.common.ApFinals;
import ap.common.ApMessage;

//Описание:
//Проверяет доступна ли авторизация - проверка для того чтоб клиент не реагировал на ответ после установленного времени ожидания.

public class AuthorizationIn {
    private ApMessage apMessage;

    public AuthorizationIn(ApMessage apMessageLocal){
        apMessage = apMessageLocal;
    }

    public void Process(){
        if (MainClientDomain.mainDomainRef.isAuthorizationAvailable()){
            AuthorizationResponseAction authorizationResponseAction = new AuthorizationResponseAction();
            if (apMessage.isAuthorizationPassed()) {
                authorizationResponseAction.AuthorizationPassed(apMessage.getLogin(), apMessage.getPassword());
            } else {
                authorizationResponseAction.AuthorizationError();
            }
        }
    }
}
