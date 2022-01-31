package ap.Domain.Net.OutMessages;

import ap.Domain.Net.OutMessages.SendMessages.SendMessageToSocketInThread;
import ap.common.ApMessage;

import java.net.Socket;

public class AuthorizationOut {
    public AuthorizationOut(ApMessage apMessage, Socket socket){
        SendMessageToSocketInThread sendMessageToSocketInThread = new SendMessageToSocketInThread(apMessage, socket);
        sendMessageToSocketInThread.start();
    }
}
