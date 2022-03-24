package ap.Domain.Net.InMessages;

import ap.Domain.Net.OutMessages.ChatChannelTextOut;
import apCommon.ApMessage;
import apCommon.ApMetaMessage;

public class ChatChannelTextIn {
    private ApMessage apMessage;
    public ChatChannelTextIn(ApMessage apMessage){
        this.apMessage = apMessage;
    }

    public void Process(){
        ApMetaMessage apMetaMessage = ap.DATA.FacadeServerDATA.MessageToData(apMessage);
        if (apMetaMessage.isAuthorizationPassed()){
            ChatChannelTextOut chatChannelTextOut = new ChatChannelTextOut(apMetaMessage);
            chatChannelTextOut.Send();
        } else {
            //TODO отправить сообщение для разлогивания.
        }
    }
}
