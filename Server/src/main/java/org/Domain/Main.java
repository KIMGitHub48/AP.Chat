package org.Domain;
/*
    Точка входа в приложение.
 */

import org.Domain.Net.Messages.chatChannelMassageInThread;

import java.net.Socket;
import java.util.ArrayList;

public class Main {
    public static Main domainMainRef;
    private static org.Domain.Net.Server server;

    public Main(String[] args) {
        System.out.println("Запущен Сервер");
        domainMainRef = this;
        org.Presentation.Facade.Launcher(args);//Запускает UI в новом потоке.
    }

    public void StartServer() {
        server = new org.Domain.Net.Server(4848);
        server.Start();//Стартует сетевую часть сервер в новом потоке.
    }

    public void StopServer() {
        server.Stop();
    }

    public void SortMessage(org.Domain.Net.Message message) {
        String id = message.getId();
        switch (id) {
            case "chatChannelText":
                ChatMessageToAll(message);
                break;
            default:
                System.out.println("ID отправляемого сообщения не найдено, ID:"+id);
        }
    }

    public void ChatMessageToAll(org.Domain.Net.Message message) {
        Thread chatMessageToAllInThread = new chatChannelMassageInThread(message);
        chatMessageToAllInThread.start();
    }

    public ArrayList<Socket> getArrayListClientSocket() {
        return server.getArrayListClientSocket();
    }
}