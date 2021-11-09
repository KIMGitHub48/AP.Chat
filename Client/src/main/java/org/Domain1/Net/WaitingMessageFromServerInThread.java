package org.Domain1.Net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class WaitingMessageFromServerInThread extends Thread {
    private Socket serverSocket;
    private BufferedReader clientSockedStreamIn; // поток чтения из сокета
    private Thread threadIn;
    private boolean threadInBreakFlag;

    WaitingMessageFromServerInThread(Socket socket) {
        serverSocket = socket;
    }

    @Override
    public void run() {
        SocketThreadInStart();
    }

    private void SocketThreadInStart() {
        org.Domain1.Net.Message message;
        threadInBreakFlag = true;
        while (threadInBreakFlag) {
            message = SocketStreamIn();
            org.DATA.Facade.MessageFromServer(message);
        }
    }

    private Message SocketStreamIn() {
        org.Domain1.Net.Message message;
        try {
            message = (Message) ClientObjectInputStream().readObject();
            return message;
        } catch (ClassNotFoundException | IOException e) {
            return null;
        }
    }

    private ObjectInputStream ClientObjectInputStream() {
        try {
            ObjectInputStream clientObjectInputStream = new ObjectInputStream(serverSocket.getInputStream());
            return clientObjectInputStream;
        } catch (IOException e) {
            return null;
        }
    }

    public boolean stopClientThread() {
        try {
            serverSocket.shutdownInput();
            threadInBreakFlag = false;
//            threadIn.interrupt();
            return true;
        } catch (IOException e) {
            return false;
        }
    } //Останавливает StreamIn, threadIn
}
