package ap.Domain.Net;

import ap.Domain.Net.OutMessages.AuthorizationOut;
import ap.Domain.Net.OutMessages.ChatChannelTextOut;
import ap.common.ApMessage;
import ap.common.ApMessageEnumType;

public class SortOutMessageInThread extends Thread {
    ApMessage apMessage;
    public SortOutMessageInThread(ApMessage apMessageLocal){
        apMessage=apMessageLocal;
    }


    @Override
    public void run() {
        ApMessageEnumType type = apMessage.getType();
        switch (type) {
            case chatChannelText:
                ChatChannelTextOut chatChannelTextOut = new ChatChannelTextOut(apMessage);
                chatChannelTextOut.Send();
                break;
            case authorization:
                AuthorizationOut authorizationOut = new AuthorizationOut(apMessage);
                authorizationOut.Send();
                break;
            default:
                System.out.println("type отправляемого сообщения не распознано");
        }
    }
}
