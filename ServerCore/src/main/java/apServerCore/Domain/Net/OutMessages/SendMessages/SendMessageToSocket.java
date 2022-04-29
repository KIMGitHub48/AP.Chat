package apServerCore.Domain.Net.OutMessages.SendMessages;

import apCommon.ApMessage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SendMessageToSocket {
    private final ApMessage apMessage;
    private final Socket socket;

    public SendMessageToSocket(ApMessage apMessage, Socket socket) {
        this.apMessage = apMessage;
        this.socket = socket;

    }

    public void Send() {
        SendMessage(socket);
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
