package ap.Domain.Net.OutMessages;

import ap.Domain.Net.OutMessages.SendMessages.SendMessageToSocket;
import ap.common.ApMessage;
import ap.common.ApMetaMessage;

import java.net.Socket;

public class AuthorizationOut {
    SendMessageToSocket sendMessageToSocket;
    public AuthorizationOut(ApMessage apMessage,Socket socket){
        sendMessageToSocket = new SendMessageToSocket(apMessage, socket);
    }

    public void Send(){
        sendMessageToSocket.Send();
    }
}
