package ap.Domain.Net.InMessages;

import ap.common.*;
import ap.Presentation.FacadeClientPresentation;

public class ChatChannelTextIn {
    private ApMessage apMessage;
    public ChatChannelTextIn(ApMessage chatChannelApMessage){
        apMessage = chatChannelApMessage;
    }
    public void Process(){
        FacadeClientPresentation.chatChannelMessage(apMessage.getChatChannelText(), apMessage.getChatChannelName());
    }
}
