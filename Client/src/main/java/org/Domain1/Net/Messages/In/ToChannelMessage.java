package org.Domain1.Net.Messages.In;

public class ToChannelMessage {
    private org.Domain1.Net.Message message;
    public ToChannelMessage(org.Domain1.Net.Message toChannelMessage){
        message = toChannelMessage;
    }
    public void Process(){
        org.Presentation.Facade.SetTextFieldChannelMessage(message.getChatChannelText(),message.getChatChannelName());
    }
}
