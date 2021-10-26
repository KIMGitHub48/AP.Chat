package org.DATA;

public class Facade {
    public static void ClientMessage(org.Domain.Net.Message message){
        //Заглушка
        org.Domain.Net.Message outMessage = new org.Domain.Net.Message();
        outMessage.setText(message.getText());

    }
}
