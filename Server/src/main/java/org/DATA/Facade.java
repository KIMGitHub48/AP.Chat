package org.DATA;

public class Facade {
    public static void MessageFromClient(org.Domain.Net.Message message){
        //Заглушка
        org.Domain.Net.Message outMessage = new org.Domain.Net.Message();
        outMessage.setText("Пришло сообщение: "+message.getText());
        org.Domain.Facade.chatMessageToAll(outMessage);
    }
}
