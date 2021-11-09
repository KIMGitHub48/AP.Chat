package org.Domain.Net;

import java.io.IOException;
import java.net.Socket;

public class ConnectToServer {
    private Thread thisThread;
    private Socket serverSocket;
    private WaitingMessageFromServerInThread waitingMessageFromServerInThread;

    public void ConnectToServer() {
        Runnable runnableStart = () -> {
            Connect();
        };
        thisThread = new Thread(runnableStart, "ConnectToServerThread");
        thisThread.start();
    }

    private Socket Connect() {
        String IP = org.Presentation.Facade.GetIPFromTextField();
        Integer Port = org.Presentation.Facade.GetPortFromTextField();
        try {
            serverSocket = new Socket(IP, Port);
            waitingMessageFromServerInThread = new WaitingMessageFromServerInThread(serverSocket);
            waitingMessageFromServerInThread.start();
            org.Presentation.Facade.AddSystemMessage("Подключение к серверу установленно.");
            return serverSocket;
        } catch (IOException e) {
            org.Presentation.Facade.AddSystemMessage("Не удалось установить подключение.");
            return null;
        }
    }

    public Socket GetServerSocket(){
        return serverSocket;
    }
}
