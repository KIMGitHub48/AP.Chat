package apServerCore.Domain.Net.OutMessages.SendMessages;

import apCommon.ApMessage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class SendMessageToArrayListSocket {
    private ArrayList<Socket> arrayListSocket;
    private  ApMessage apMessage;

    public SendMessageToArrayListSocket(ApMessage apMessage, ArrayList<Socket> arrayListSocket){
        this.arrayListSocket = arrayListSocket;
        this.apMessage = apMessage;
    }

    public void Send() {
        for (Socket element : arrayListSocket) {
            SendMessage(element);
        }
    }

    public void SendMessage(Socket clientSocket) {
        int icnCheck = 0;
        while (icnCheck < 3) {
            try {
                GetOutputStream(clientSocket).writeObject(apMessage);
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
