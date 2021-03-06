package apServerCore.Domain.Net.InMessages;

import apServerCore.DATA.DATAService;
import apServerCore.Domain.Net.OutMessages.ChatChannelTextOut;
import apCommon.ApMessage;
import apCommon.ApMetaMessage;

public class ChatChannelTextIn {
    private DATAService dataService = DATAService.getFirst();
    private ApMessage apMessage;
    public ChatChannelTextIn(ApMessage apMessage){
        this.apMessage = apMessage;
    }

    public void Process(){
        ApMetaMessage apMetaMessage = dataService.MessageToData(apMessage);
        if (apMetaMessage.isAuthorizationPassed()){
            ChatChannelTextOut chatChannelTextOut = new ChatChannelTextOut(apMetaMessage);
            chatChannelTextOut.Send();
        } else {
            //TODO отправить сообщение для разлогивания.
        }
    }
}
