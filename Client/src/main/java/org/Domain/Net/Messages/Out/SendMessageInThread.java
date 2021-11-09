package org.Domain.Net.Messages.Out;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SendMessageInThread extends Thread {
    private Socket serverSocket;
    private org.Domain.Net.Message message;

    public SendMessageInThread(Socket socket, org.Domain.Net.Message outMessage) {
        serverSocket = socket;
        message = outMessage;
    }

    @Override
    public void run() {
        int icnCheck = 0;
        while (icnCheck < 3) {
            try {
                GetOutputStream().writeObject(message);
                icnCheck = 3;
            } catch (IOException e) {
                icnCheck++;
            }
        }
    } //Повторяет попытку 3 раза

    private ObjectOutputStream GetOutputStream() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(serverSocket.getOutputStream());
            return objectOutputStream;
        } catch (IOException e) {
            return null;
        }
    }
}
