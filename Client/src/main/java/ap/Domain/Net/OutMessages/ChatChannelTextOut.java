package ap.Domain.Net.OutMessages;

import ap.Domain.MainClientDomain;
import ap.Domain.Net.SendMessageInThread;
import ap.common.ApMessage;

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
