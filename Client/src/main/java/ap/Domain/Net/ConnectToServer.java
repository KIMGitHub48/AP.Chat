package ap.Domain.Net;

import ap.Presentation.FacadeClientPresentation;

import java.io.IOException;
import java.net.Socket;

public class ConnectToServer {
    private Thread thisThread;
    private Socket serverSocket;
    private WaitingMessageFromServerInThread waitingMessageFromServerInThread;

    public void ConnectToServer(String IP,Integer Port) {
        Runnable runnableStart = () -> {
            Connect(IP,Port);
        };
        thisThread = new Thread(runnableStart, "ConnectToServerThread");
        thisThread.start();
    }

    private void Connect(String IP,Integer Port) {
        try {
            serverSocket = new Socket(IP, Port);
            waitingMessageFromServerInThread = new WaitingMessageFromServerInThread(serverSocket);
            waitingMessageFromServerInThread.start();
            FacadeClientPresentation.AddSystemMessage("Подключение к серверу установленно.");
        } catch (IOException e) {
            FacadeClientPresentation.AddSystemMessage("Не удалось установить подключение.");
        }
    }
    public boolean IsConnected(){
        if (serverSocket.isConnected()){
            return true;
        } else {
            return false;
        }
    }
    public Socket GetServerSocket(){
        return serverSocket;
    }
}
