package org.Domain.Net;

import org.DATA.ApacheDerby.ApacheDerby;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.security.AlgorithmParameterGenerator;

public class WaitingMessageFromClientInThread extends Thread {
    private Socket clientSocket;
    private BufferedReader clientSockedStreamIn; // поток чтения из сокета
    private Thread threadIn;
    private boolean threadInBreakFlag;

    WaitingMessageFromClientInThread(Socket socket) {
        clientSocket = socket;
    }

    @Override
    public void run() {
        SocketThreadInStart();
    }

    private void SocketThreadInStart() {
        org.Domain.Net.Message message;
        threadInBreakFlag = true;
        while (threadInBreakFlag) {
            message = SocketStreamIn();
            ApacheDerby.addRecord(message.getChatChannelText());// запись сообщения в базу данных
            org.DATA.Facade.MessageFromClient(message);
        }
    }

    private org.Domain.Net.Message SocketStreamIn() {
        org.Domain.Net.Message message;
        try {
            message = (org.Domain.Net.Message) ClientObjectInputStream().readObject();
            return message;
        } catch (ClassNotFoundException | IOException e) {
            return null;
        }
    }

    private ObjectInputStream ClientObjectInputStream() {
        try {
            ObjectInputStream clientObjectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            return clientObjectInputStream;
        } catch (IOException e) {
            return null;
        }
    }

    public boolean stopClientThread() {
        try {
            clientSocket.shutdownInput();
            threadInBreakFlag = false;
//            threadIn.interrupt();
            return true;
        } catch (IOException e) {
            return false;
        }
    } //Останавливает StreamIn, threadIn
}
