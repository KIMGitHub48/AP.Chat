package ap.Domain.Net;

import ap.Domain.Net.InMessages.AuthorizationIn;
import ap.Domain.Net.InMessages.ChatChannelTextIn;
import ap.common.ApMessage;
import ap.common.ApMessageEnumType;

public class SortMessageInThread extends Thread {
    private ApMessage apMessage;

    public SortMessageInThread(ApMessage apMessageLocal){
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
                System.out.println("type отправляемого сообщения не распознано");
        }
    }
}
