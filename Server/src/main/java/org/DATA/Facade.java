package org.DATA;

public class Facade {
    public static void MessageFromClient(org.Domain.Net.Message message){
        //Заглушка
        org.Domain.Net.Message outMessage = new org.Domain.Net.Message();
        org.Presentation.Facade.AddSystemMessage("Пришло сообщение: "+message.getChatChannelText()+" Для канала:"+message.getChatChannelName());
        outMessage.setChatChannelText("На сервер пришло сообщение: "+message.getChatChannelText()+" Для канала:"+message.getChatChannelName());
        outMessage.setChatChannelName(message.getChatChannelName());
        org.Domain.Facade.SendMessage(outMessage);
    }
}
