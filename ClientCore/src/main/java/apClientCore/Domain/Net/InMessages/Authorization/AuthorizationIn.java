package apClientCore.Domain.Net.InMessages.Authorization;

import apClientCore.Domain.MainClientCoreService;
import apClientCore.Domain.MainClientDomain;
import apCommon.ApMessage;

//Описание:
//Проверяет доступна ли авторизация - проверка для того чтоб клиент не реагировал на ответ после установленного времени ожидания.

public class AuthorizationIn {
    private ApMessage apMessage;
    private MainClientCoreService mainClientCoreService = MainClientCoreService.getFirst();

    public AuthorizationIn(ApMessage apMessage){
        this.apMessage = apMessage;
    }

    public void Process(){
        if (mainClientCoreService.isAuthorizationAvailable()){
            AuthorizationResponseAction authorizationResponseAction = new AuthorizationResponseAction();
            if (apMessage.isAuthorizationPassed()) {
                authorizationResponseAction.AuthorizationPassed(apMessage.getLogin(), apMessage.getPassword());
            } else {
                authorizationResponseAction.AuthorizationIsNotPassed();
            }
        }
    }
}
