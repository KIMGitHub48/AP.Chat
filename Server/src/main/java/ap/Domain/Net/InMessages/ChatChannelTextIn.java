package ap.Domain.Net.InMessages;

import ap.Domain.Net.OutMessages.ChatChannelTextOut;
import ap.common.ApMessage;
import ap.common.ApMetaMessage;

import java.util.ArrayList;

public class ChatChannelTextIn {
    private ApMessage apMessage;
    public ChatChannelTextIn(ApMessage apMessage){
        this.apMessage = apMessage;
    }

    public void Process(){
        ApMetaMessage apMetaMessage = ap.DATA.FacadeServerDATA.MessageToData(apMessage);
        if (apMetaMessage.isAuthorizationPassed()){
            ChatChannelTextOut chatChannelTextOut = new ChatChannelTextOut(apMetaMessage);
        } else {
            //TODO отправить сообщение для разлогивания.
        }
    }
}
