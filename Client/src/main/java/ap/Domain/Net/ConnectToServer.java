package ap.Domain.Net;

import ap.Presentation.FacadeClientPresentation;

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
        String IP = FacadeClientPresentation.GetIPFromTextField();
        Integer Port = FacadeClientPresentation.GetPortFromTextField();
        try {
            serverSocket = new Socket(IP, Port);
            waitingMessageFromServerInThread = new WaitingMessageFromServerInThread(serverSocket);
            waitingMessageFromServerInThread.start();
            FacadeClientPresentation.AddSystemMessage("Подключение к серверу установленно.");
            return serverSocket;
        } catch (IOException e) {
            FacadeClientPresentation.AddSystemMessage("Не удалось установить подключение.");
            return null;
        }
    }

    public Socket GetServerSocket(){
        return serverSocket;
    }
}
