package apClientCore.Core.Net.OutMessages;

import apClientCore.Core.MainClientCoreService;
import apClientCore.Core.Net.SendMessageInThread;
import apCommon.ApMessage;

import java.net.Socket;

public class AuthorizationOut {
    private ApMessage apMessage;
    private MainClientCoreService mainClientCoreService = MainClientCoreService.getFirst();

    public AuthorizationOut(ApMessage apMessageLocal){
        apMessage = apMessageLocal;
    }

    public void Send(){
        Socket serverSocket = mainClientCoreService.GetServerSocket();
        if (serverSocket != null) {
            SendMessageInThread sendMessageInThread = new SendMessageInThread(serverSocket, apMessage);
            sendMessageInThread.start();
        }
    }
}
