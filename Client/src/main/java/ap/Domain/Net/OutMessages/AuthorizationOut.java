package ap.Domain.Net.OutMessages;

import ap.Domain.MainClientDomain;
import ap.Domain.Net.SendMessageInThread;
import ap.common.*;

import java.net.Socket;
import java.util.UUID;

public class AuthorizationOut {
    ApMessage apMessage;
    public AuthorizationOut(ApMessage apMessageLocal){
        apMessage = apMessageLocal;
    }


    public void Send(){
        Socket serverSocket = MainClientDomain.mainDomainRef.GetServerSocket();
        if (serverSocket != null) {
            SendMessageInThread sendMessageInThread = new SendMessageInThread(serverSocket, apMessage);
            sendMessageInThread.start();
        }
    }
}
