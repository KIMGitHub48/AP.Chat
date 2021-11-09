package org.DATA;

public class Facade {
    public static void MessageFromClient(org.Domain1.Net.Message message){
        //Заглушка
        org.Domain1.Net.Message outMessage = new org.Domain1.Net.Message();
        org.Presentation.Facade.AddSystemMessage("Пришло сообщение: "+message.getChatChannelText()+" Для канала:"+message.getChatChannelName());
        outMessage.setChatChannelText("На сервер пришло сообщение: "+message.getChatChannelText()+" Для канала:"+message.getChatChannelName());
        outMessage.setChatChannelName(message.getChatChannelName());
        org.Domain1.Facade.SendMessage(outMessage);
    }
}
