package org.Domain;

import org.Domain.Net.ClientThread;

import java.net.Socket;
import java.util.ArrayList;

public class Facade {
    public static void SendMessage(org.Domain.Net.Message message) {
        String id = message.getId();
        switch (id) {
            case ("chatMessageToAll"):
                chatMessageToAll(message);
            default:
                System.out.println("ID отправляемого сообщения не найдено");
        }
    }

    public static void chatMessageToAll(org.Domain.Net.Message message){
        ArrayList<Socket>  arrayListClientSocket = Main.mainRef.getArrayListClientSocket();
        for (int i = 0; i < arrayListClientSocket.size(); i++) {
            Socket element = arrayListClientSocket.get(i);
            //(ClientThread)element.
        }
    }
}
