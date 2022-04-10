package apClientCore.Core.Net;

import apClientCore.Core.Net.OutMessages.AuthorizationOut;
import apClientCore.Core.Net.OutMessages.ChatChannelTextOut;
import apCommon.ApMessage;
import apCommon.ApMessageEnumType;

public class SortOutMessageInThread extends Thread {
    ApMessage apMessage;
    public SortOutMessageInThread(ApMessage apMessage){
        this.apMessage =apMessage;
    }


    @Override
    public void run() {
        ApMessageEnumType type = apMessage.getType();
        switch (type) {
            case authorization:
                AuthorizationOut authorizationOut = new AuthorizationOut(apMessage);
                authorizationOut.Send();
                break;
            case chatChannelText:
                ChatChannelTextOut chatChannelTextOut = new ChatChannelTextOut(apMessage);
                chatChannelTextOut.Send();
                break;
            default:
                System.out.println("type отправляемого сообщения не распознано");
        }
    }
}
