package ap.Domain.Net.OutMessages;

import ap.Domain.MainClientDomain;
import ap.common.*;
import java.util.UUID;

public class AuthorizationOut {
    private String login;
    private String password;
    //++++++++++++++++++++++++
    public AuthorizationOut(String loginFromOutside, String passwordFromOutside){
        login = loginFromOutside;
        password = passwordFromOutside;
    }
    //++++++++++++++++++++++++
    private ApMessage CreateMessage(){
        ApMessage apMessage = new ApMessage();
        apMessage.setType(ApMessageEnumType.authorization);
        apMessage.setUUID(UUID.randomUUID());
        apMessage.setLogin(login);
        apMessage.setPassword(password);
        return apMessage;
    }
    public void Send(){
        ApMessage apMessage = CreateMessage();
        MainClientDomain.mainDomainRef.SendMessage(apMessage);
    }
}
