package apClient.Presentation.Messages;

import apClient.Domain.FacadeClientDomain;
import apClient.Presentation.MainClientPresentation;
import apCommon.ApMessage;
import apCommon.ApMessageEnumType;

import java.util.UUID;

public class ChatChannelSendButtonInThread extends Thread {

    @Override
    public void run(){
        ApMessage apMessage = new ApMessage();
        apMessage.setLogin(FacadeClientDomain.GetLogin());
        apMessage.setPassword(FacadeClientDomain.GetPassword());
        apMessage.setType(ApMessageEnumType.chatChannelText);
        apMessage.setUUID(UUID.randomUUID());
        apMessage.setChatChannelText(MainClientPresentation.mainPresentationRef.GetTextFromChatMessageTextField());
        apMessage.setChatChannelName("Default");

        FacadeClientDomain.SortOutMessageInThread(apMessage);
    }
}
