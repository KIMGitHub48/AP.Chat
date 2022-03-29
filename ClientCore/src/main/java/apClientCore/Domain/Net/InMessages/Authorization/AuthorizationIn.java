package apClientCore.Domain.Net.InMessages.Authorization;

import apClientCore.Domain.MainClientDomain;
import apCommon.ApMessage;

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
