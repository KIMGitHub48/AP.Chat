package apClient.Domain.Net.OutMessages;

import apClient.Domain.MainClientDomain;
import apClient.Domain.Net.SendMessageInThread;
import apCommon.*;
import apCommon.ApMessage;

import java.net.Socket;

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
