package org.Domain;

import org.Domain.Net.ClientThread;

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

    private static void chatMessageToAll(org.Domain.Net.Message message){
        ArrayList<Thread>  arrayListClientThread = Main.mainRef.getArrayListClientThread();
        for (int i = 0; i < arrayListClientThread.size(); i++) {
            Thread element = arrayListClientThread.get(i);
            //(ClientThread)element.
        }
    }
}
