package apClientCore.Domain.Net.OutMessages;

import apClientCore.Domain.MainClientDomain;
import apClientCore.Domain.Net.SendMessageInThread;
import apCommon.ApMessage;

import java.net.Socket;

public class ChatChannelTextOut {
    ApMessage apMessage;
    public ChatChannelTextOut(ApMessage apMessage){
        this.apMessage = apMessage;
    }


    public void Send(){
        Socket serverSocket = MainClientDomain.mainDomainRef.GetServerSocket();
        if (serverSocket != null) {
            SendMessageInThread sendMessageInThread = new SendMessageInThread(serverSocket, apMessage);
            sendMessageInThread.start();
        }
    }
}
