package org.Domain.Net.Messages.In;

public class ChatChannelText {
    private org.Domain.Net.Message message;
    public ChatChannelText(org.Domain.Net.Message chatChannelMessage){
        message = chatChannelMessage;
    }
    public void Process(){
        org.Presentation.Facade.chatChannelMessage(message.getChatChannelText(),message.getChatChannelName());
    }
}
