package ap.Presentation.Messages;

import ap.Domain.FacadeClientDomain;
import ap.Presentation.MainClientPresentation;
import ap.common.ApMessage;
import ap.common.ApMessageEnumType;

import java.util.UUID;

public class ChatChannelSendButtonInThread extends Thread {

    @Override
    public void run(){
        ApMessage apMessage = new ApMessage();
        apMessage.setLogin(ap.Domain.FacadeClientDomain.GetLogin());
        apMessage.setPassword(ap.Domain.FacadeClientDomain.GetPassword());
        apMessage.setType(ApMessageEnumType.chatChannelText);
        apMessage.setUUID(UUID.randomUUID());
        apMessage.setChatChannelText(MainClientPresentation.mainPresentationRef.GetTextFromChatMessageTextField());
        apMessage.setChatChannelName("Default");

        FacadeClientDomain.SortOutMessageInThread(apMessage);
    }
}
