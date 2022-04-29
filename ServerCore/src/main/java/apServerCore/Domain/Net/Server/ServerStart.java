package apServerCore.Domain.Net.Server;


import apServerCore.Presentation.FacadeServerPresentation;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/*
    Запускает Сервер в новом потоке ServerThread который при подключении создаст объект ClientThread и отдаст ему управление.
 */

public class ServerStart {
    private ServerSocket serverSocket;
    private int serverPort;
    private ArrayList<Thread> arrayListClientThread = new ArrayList();
    private Thread thisThread;
    private ArrayList<Socket> arrayListClientSocket = new ArrayList();

    public ServerStart(int port) {
        serverPort = port;
    }

    public void Start() {
        Runnable runnableStart = () -> {
            StartServerSocket();
        };
        thisThread = new Thread(runnableStart, "ServerThread");
        thisThread.start();
    }

    public void Stop() {
        try {
            serverSocket.close();
            FacadeServerPresentation.AddTextToStartServerButtonAfterStop();
            FacadeServerPresentation.AddSystemMessage("Сервер остановлен");
            thisThread.interrupt();
        } catch (IOException e) {
            System.out.println("Ошибка остановки сервера");
        }
    }

    private void StartServerSocket() {
        try {
            serverSocket = new ServerSocket(serverPort);
            FacadeServerPresentation.AddSystemMessage("Сервер запущен на порту: " + serverPort);
            FacadeServerPresentation.AddTextToStartServerButtonAfterStart();
            while (true) {
                addClientSocketToThread(serverSocket);
            }
        } catch (IOException e) {
            System.out.println("StartServerSocket - Error");//TODO Сервер не запускается. Создать объект ServerStartError
        }
    }

    private void addClientSocketToThread(ServerSocket serverSocket) throws IOException {
        Socket clientSocket;
        clientSocket = serverSocket.accept();
        FacadeServerPresentation.AddSystemMessage("Успешно подключен клиент.");
        arrayListClientSocket.add(clientSocket);
        Thread serverWaitingMessageThread = new WaitingMessageFromClientInThread(clientSocket);
        serverWaitingMessageThread.start();
    }

    public ArrayList<Socket> getArrayListClientSocket() {
        return arrayListClientSocket;
    }

}
