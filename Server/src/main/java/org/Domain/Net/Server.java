package org.Domain.Net;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/*
    Запускает Сервер в новом потоке ServerThread который при подключении создаст объект ClientThread и отдаст ему управление.
 */

public class Server {
    private ArrayList<Thread> arrayListClientThread = new ArrayList();

    public void Start(){
        Runnable mainRunnable = () -> {
            StartServerSocket();
        };
        new Thread(mainRunnable, "ServerThread").start();
    }

    private void StartServerSocket () {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(4848);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            addClientSocket(serverSocket);
        }
    }
    private void addClientSocket(ServerSocket serverSocket){
        Socket clientSocket = null;
        while (true) {
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                Thread clientThread = new ClientThread(clientSocket);
                arrayListClientThread.add(clientThread);
                clientThread.start();
            }
        }
    }
}
