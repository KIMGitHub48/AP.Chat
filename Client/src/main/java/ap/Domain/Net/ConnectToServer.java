package ap.Domain.Net;

import ap.Presentation.Facade;

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
        String IP = Facade.GetIPFromTextField();
        Integer Port = Facade.GetPortFromTextField();
        try {
            serverSocket = new Socket(IP, Port);
            waitingMessageFromServerInThread = new WaitingMessageFromServerInThread(serverSocket);
            waitingMessageFromServerInThread.start();
            Facade.AddSystemMessage("Подключение к серверу установленно.");
            return serverSocket;
        } catch (IOException e) {
            Facade.AddSystemMessage("Не удалось установить подключение.");
            return null;
        }
    }

    public Socket GetServerSocket(){
        return serverSocket;
    }
}
