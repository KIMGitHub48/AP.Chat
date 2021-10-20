package org.Domain.Net;

import java.net.Socket;

public class ClientThread extends Thread {
    private Socket clientSocket;

    ClientThread(Socket socket){
        clientSocket = socket;
    }

    @Override
    public void run() {
        System.out.println("Поток запущен");
    }
}
