package ap.Domain.Net;

import ap.DATA.ApacheDerby.ApacheDerby;
import ap.DATA.FacadeServerDATA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

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
        Message message;
        threadInBreakFlag = true;
        while (threadInBreakFlag) {
            message = SocketStreamIn();
            ApacheDerby.addRecord(message.getChatChannelText());// запись сообщения в базу данных
            FacadeServerDATA.MessageFromClient(message);
        }
    }

    private Message SocketStreamIn() {
        Message message;
        try {
            message = (Message) ClientObjectInputStream().readObject();
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
