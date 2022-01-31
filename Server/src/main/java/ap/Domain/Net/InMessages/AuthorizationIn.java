package ap.Domain.Net.InMessages;

import ap.Domain.Net.OutMessages.AuthorizationOut;
import ap.common.ApMessage;

import java.net.Socket;

public class AuthorizationIn {
    private ApMessage apMessage;
    private Socket socket;
    public AuthorizationIn(ApMessage apMessageLocal, Socket socket){
        apMessage = apMessageLocal;
        this.socket = socket;
    }

    public void Process(){
        String login = apMessage.getLogin();
        String password = apMessage.getPassword();
        boolean authorizationPassed = ap.DATA.FacadeServerDATA.Authorization(login, password);
        apMessage.setAuthorizationPassed(authorizationPassed);

        ap.Domain.Net.OutMessages.AuthorizationOut authorizationOut = new AuthorizationOut(apMessage, socket);
    }
}
