package apClientAuthorization.Messages.In;

import apClientAuthorization.MainClientAuthorization;
import apCommon.ApMessage;

//Описание:
//Проверяет доступна ли авторизация - проверка для того чтобы ClientAuthorization не реагировал на ответ после установленного времени ожидания.

public class AuthorizationIn {
    private ApMessage apMessage;
    private MainClientAuthorization main = MainClientAuthorization.mainClientAuthorizationRef;

    public AuthorizationIn(ApMessage apMessageLocal){
        apMessage = apMessageLocal;
    }

    public void Process(){
        if (main.isAuthorizationAvailable()){
            AuthorizationResponseAction authorizationResponseAction = new AuthorizationResponseAction();
            if (apMessage.isAuthorizationPassed()) {
                authorizationResponseAction.AuthorizationPassed(apMessage.getLogin(), apMessage.getPassword());
            } else {
                authorizationResponseAction.AuthorizationError();
            }
        }
    }
}
