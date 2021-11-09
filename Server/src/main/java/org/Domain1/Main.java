package org.Domain1;
/*
    Точка входа в приложение.
 */

import org.Domain1.Net.Messages.ChatMessageToAllInThread;

import java.net.Socket;
import java.util.ArrayList;

public class Main {
    public static Main domainMainRef;
    private static org.Domain1.Net.Server server;

    public Main(String[] args) {
        domainMainRef = this;
        org.Presentation.Facade.Launcher(args);//Запускает UI в новом потоке.
    }

    public ArrayList<Thread> getArrayListClientThread() {
        return server.getArrayListClientThread();
    }

    public ArrayList<Socket> getArrayListClientSocket() {
        return server.getArrayListClientSocket();
    }

    public void StartServer() {
        server = new org.Domain1.Net.Server(4848);
        server.Start();//Стартует сетевую часть сервер в новом потоке.
    }

    public void StopServer() {
        server.Stop();
    }

    public void SortMessage(org.Domain1.Net.Message message) {
        String id = message.getId();
        switch (id) {
            case ("chatMessageToAll"):
                ChatMessageToAll(message);
            default:
                System.out.println("ID отправляемого сообщения не найдено");
        }
    }

    public void ChatMessageToAll(org.Domain1.Net.Message message) {
        Thread chatMessageToAllInThread = new ChatMessageToAllInThread(message);
        chatMessageToAllInThread.start();
    }
}