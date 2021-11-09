package org.Domain1.Net.Messages;

import org.Domain1.Main;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ChatMessageToAllInThread extends Thread {
    private org.Domain1.Net.Message message;

    public ChatMessageToAllInThread(org.Domain1.Net.Message outMessage) {
        message = outMessage;
    }

    @Override
    public void run() {
        ArrayList<Socket> arrayListClientSocket = Main.domainMainRef.getArrayListClientSocket();
        for (Socket element : arrayListClientSocket) {
            SendMessage(element);
        }
    }

    public void SendMessage(Socket clientSocket) {
        int icnCheck = 0;
        while (icnCheck < 3) {
            try {
                GetOutputStream(clientSocket).writeObject(message);
                icnCheck = 3;
            } catch (IOException e) {
                icnCheck++;
            }
        }
    } //Повторяет попытку 3 раза

    private ObjectOutputStream GetOutputStream(Socket clientSocket) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            return objectOutputStream;
        } catch (IOException e) {
            return null;
        }
    }
}
