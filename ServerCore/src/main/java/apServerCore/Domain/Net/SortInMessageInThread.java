package apServerCore.Domain.Net;

import apServerCore.Domain.Net.InMessages.AuthorizationIn;
import apCommon.ApMessage;
import apCommon.ApMessageEnumType;
import apServerCore.Domain.Net.InMessages.ChatChannelTextIn;

import java.net.Socket;

public class SortInMessageInThread extends Thread {
    private ApMessage apMessage;
    private Socket socket;

    public SortInMessageInThread(ApMessage apMessage, Socket socket){
        this.apMessage = apMessage;
        this.socket = socket;
    }


    @Override
    public void run() {
        ApMessageEnumType type = apMessage.getType();
        switch (type) {
            case chatChannelText:
                ChatChannelTextIn chatChannelTextIn = new ChatChannelTextIn(apMessage);
                chatChannelTextIn.Process();
                break;
            case authorization:
                AuthorizationIn authorizationIn = new AuthorizationIn(apMessage, socket);
                authorizationIn.Process();
                break;
            default:
                System.out.println("type принимаемого сообщения не распознано");
                break;
        }
    }
}
