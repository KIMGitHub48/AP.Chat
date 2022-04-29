package apServerCore.Domain.Net.OutMessages;

import apServerCore.Domain.Net.OutMessages.SendMessages.SendMessageToSocket;
import apCommon.ApMessage;

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
