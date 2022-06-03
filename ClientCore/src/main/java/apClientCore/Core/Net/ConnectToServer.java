package apClientCore.Core.Net;

//import apClientCore.Presentation.FacadeClientPresentation;
import java.io.IOException;
import java.net.Socket;

public class ConnectToServer {
    private Thread thisThread;
    private Socket serverSocket;
    private WaitingMessageFromServerInThread waitingMessageFromServerInThread;

    public void ConnectToServer(String IP,Integer Port) {
        System.out.println("Лбъе");
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
            //TODO FacadeClientPresentation.AddSystemMessage(ApFinals.SYSTEM_MESSAGE_SERVER_CONNECT);
            System.out.println("Connect установлен");
        } catch (IOException e) {
            //TODO FacadeClientPresentation.AddSystemMessage(ApFinals.SYSTEM_MESSAGE_SERVER_CONNECT_ERROR);
            System.out.println("Connect НЕ установлен");
        }
    }
    public boolean IsConnected(){
        if ((serverSocket != null)&&(serverSocket.isConnected())){
            return true;
        } else {
            return false;
        }
    }
    public Socket GetServerSocket(){
        return serverSocket;
    }
}
