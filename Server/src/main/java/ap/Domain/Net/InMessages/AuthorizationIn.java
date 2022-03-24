package ap.Domain.Net.InMessages;

import ap.Domain.MainServerDomain;
import ap.Domain.Net.OutMessages.AuthorizationOut;
import apCommon.ApMessage;
import apCommon.ApMetaMessage;

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
        ApMessage apMessageFromApMetaMessage = apMetaMessage.getApMessage();
        ap.Domain.Net.OutMessages.AuthorizationOut authorizationOut = new AuthorizationOut(apMessageFromApMetaMessage, socket);
        authorizationOut.Send();
        if (apMetaMessage.isAuthorizationPassed()) {
            MainServerDomain.mainServerDomainRef.AddActualLoginInfo(apMessage.getLogin(), socket);
        }
    }
}
