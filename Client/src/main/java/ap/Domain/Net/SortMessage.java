package ap.Domain.Net;

import ap.Domain.MainClientDomain;
import ap.Domain.Net.InMessages.ChatChannelText;
import ap.common.ApMessage;
import ap.common.ApMessageEnumType;

public class SortMessage {
    private void SortMessage(ApMessage apMessage) {
        ApMessageEnumType type = apMessage.getType();
        switch (type) {
            case chatChannelText:
                ChatChannelText chatChannelText = new ChatChannelText(apMessage);
                chatChannelText.Process();
                break;
            case authorization:
                MainClientDomain.mainDomainRef.AddApMessageToPresentationList(apMessage);
                //AuthorizationResponse(apMessage);
                break;
            default:
                System.out.println("type отправляемого сообщения не распознано");
        }
    }
}
