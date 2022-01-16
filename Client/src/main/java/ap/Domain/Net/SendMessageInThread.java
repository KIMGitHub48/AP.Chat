package ap.Domain.Net;

import ap.common.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SendMessageInThread extends Thread {
    private Socket serverSocket;
    private ApMessage apMessage;
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public SendMessageInThread(Socket socket, ApMessage outApMessage) {
        serverSocket = socket;
        apMessage = outApMessage;
    }
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @Override
    public void run() {
        int icnCheck = 0;
        while (icnCheck < 3) {
            try {
                GetOutputStream().writeObject(apMessage);
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
