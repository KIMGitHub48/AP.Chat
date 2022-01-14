package ap.Domain.Net.Messages.In;

import ap.Domain.Net.Message;
import ap.Presentation.FacadeClientPresentation;

public class ChatChannelText {
    private Message message;
    public ChatChannelText(Message chatChannelMessage){
        message = chatChannelMessage;
    }
    public void Process(){
        FacadeClientPresentation.chatChannelMessage(message.getChatChannelText(),message.getChatChannelName());
    }
}
