package org.Domain.Net.Send;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ToAll extends Thread {
    private Socket clientSocket;
    private org.Domain.Net.Message message;

    public ToAll(Socket socket, org.Domain.Net.Message outMessage) {
        clientSocket = socket;
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
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            return objectOutputStream;
        } catch (IOException e) {
            return null;
        }
    }
}
