package ap.Domain.Net.InMessages;

import ap.Domain.MainServerDomain;
import ap.Domain.Net.OutMessages.AuthorizationOut;
import ap.common.ApMessage;
import ap.common.ApMetaMessage;

import java.net.Socket;

public class AuthorizationIn {
    private ApMessage apMessage;
    private Socket socket;
    public AuthorizationIn(ApMessage apMessageLocal, Socket socket){
        apMessage = apMessageLocal;
        this.socket = socket;
    }

    public void Process(){
        ApMetaMessage apMetaMessage = ap.DATA.FacadeServerDATA.MessageToData(apMessage);
        if (apMetaMessage.isAuthorizationPassed()) {
            ApMessage apMessageFromApMetaMessage = apMetaMessage.getApMessage();
            ap.Domain.Net.OutMessages.AuthorizationOut authorizationOut = new AuthorizationOut(apMessageFromApMetaMessage, socket);
            authorizationOut.Send();
            MainServerDomain.mainServerDomainRef.AddActualLoginInfo(apMessage.getLogin(),socket);
        }
    }
}
