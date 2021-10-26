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

    public ArrayList<Thread> getArrayListClientThread() {
        return arrayListClientThread;
    }

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
            addClientSocketCycle(serverSocket);
        } catch (IOException e) {
            System.out.println("StartServerSocket - Error");//TODO Сделать чтото реальное
        }
    }
    private boolean addClientSocketCycle(ServerSocket serverSocket){
        Socket clientSocket = null;
        while (true) {
            try {
                clientSocket = serverSocket.accept();
                Thread clientThread = new ClientThread(clientSocket);
                arrayListClientThread.add(clientThread);
                clientThread.start();
            } catch (IOException e) {
                System.out.println("addClientSocketCycle - Error");//TODO Сделать чтото реальное
            }
        }
    }

}
