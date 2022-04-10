package apClientCore.Core.Net;

import apClientCore.Core.Net.InMessages.Authorization.AuthorizationIn;
import apClientCore.Core.Net.InMessages.ChatChannelTextIn;
import apCommon.ApMessage;
import apCommon.ApMessageEnumType;

public class SortInMessageInThread extends Thread {
    private ApMessage apMessage;

    public SortInMessageInThread(ApMessage apMessageLocal){
        apMessage = apMessageLocal;
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
                AuthorizationIn authorizationIn = new AuthorizationIn(apMessage);
                authorizationIn.Process();
                break;
            default:
                System.out.println("type принимаемого сообщения не распознано");
        }
    }
}
