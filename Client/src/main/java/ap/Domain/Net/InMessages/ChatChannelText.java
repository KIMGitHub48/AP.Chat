package ap.Domain.Net.InMessages;

import ap.common.*;
import ap.Presentation.FacadeClientPresentation;

public class ChatChannelText {
    private ApMessage apMessage;
    public ChatChannelText(ApMessage chatChannelApMessage){
        apMessage = chatChannelApMessage;
    }
    public void Process(){
        FacadeClientPresentation.chatChannelMessage(apMessage.getChatChannelText(), apMessage.getChatChannelName());
    }
}
