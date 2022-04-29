package apServerCore.Domain.Net.OutMessages;

import apServerCore.Domain.MainServerDomain;
import apServerCore.Domain.Net.OutMessages.SendMessages.SendMessageToArrayListSocket;
import apCommon.ApMessage;
import apCommon.ApMetaMessage;

import java.net.Socket;
import java.util.ArrayList;

public class ChatChannelTextOut {
    private ApMetaMessage apMetaMessage;
    private ApMessage apMessage;
    public ChatChannelTextOut(ApMetaMessage apMetaMessage){
        this.apMetaMessage = apMetaMessage;
        apMessage = apMetaMessage.getApMessage();
    }

    public void Send(){
        ArrayList<String> loginsFromMeta = apMetaMessage.getLoginsPassedToChannel();
        ArrayList<Socket> compareSocketList = MainServerDomain.mainServerDomainRef.CompareActualLoginsWithMeta(loginsFromMeta);
        SendMessageToArrayListSocket sendMessageToArrayListSocket = new SendMessageToArrayListSocket(apMessage, compareSocketList);
        sendMessageToArrayListSocket.Send();

    }
}
