package org.Domain.Net;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    public void Start (){
        try {
            ServerSocket serverSocket = new ServerSocket(4848);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ClientThread thread0 = new ClientThread(null);
            thread0.start();
        }
    }
}
