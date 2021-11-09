package org.DATA;

public class Facade {
    public static void MessageFromClient(org.Domain.Net.Message message){
        //Заглушка
        org.Presentation.Facade.AddSystemMessage("Пришло сообщение: ID:"+message.getId()+" Text:"+message.getChatChannelText()+" Channel:"+message.getChatChannelName());
        message.setChatChannelText("На сервер пришло сообщение: ID:"+message.getId()+" Text:"+message.getChatChannelText()+" Channel:"+message.getChatChannelName());
        org.Domain.Facade.SendMessage(message);
    }
}
