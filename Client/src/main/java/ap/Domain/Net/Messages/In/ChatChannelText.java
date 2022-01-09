package ap.Domain.Net.Messages.In;

import ap.Domain.Net.Message;
import ap.Presentation.Facade;

public class ChatChannelText {
    private Message message;
    public ChatChannelText(Message chatChannelMessage){
        message = chatChannelMessage;
    }
    public void Process(){
        Facade.chatChannelMessage(message.getChatChannelText(),message.getChatChannelName());
    }
}
