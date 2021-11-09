package org.Domain.Net.Messages.In;

public class ToChannelMessage {
    private org.Domain.Net.Message message;
    public ToChannelMessage(org.Domain.Net.Message toChannelMessage){
        message = toChannelMessage;
    }
    public void Process(){
        org.Presentation.Facade.SetTextFieldChannelMessage(message.getChatChannelText(),message.getChatChannelName());
    }
}
