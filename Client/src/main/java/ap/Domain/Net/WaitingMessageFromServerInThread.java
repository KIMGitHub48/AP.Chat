package ap.Domain.Net;

import ap.DATA.FacadeClientDATA;
import ap.Domain.FacadeClientDomain;
import ap.common.*;
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
        ApMessage apMessage;
        threadInBreakFlag = true;
        while (threadInBreakFlag) {
            apMessage = SocketStreamIn();
            FacadeClientDomain.SortMessageInNewThread(apMessage);
        }
    }
//test
    private ApMessage SocketStreamIn() {
        ApMessage apMessage;
        try {
            ObjectInputStream objectInputStream = ClientObjectInputStream();
            apMessage = (ApMessage) objectInputStream.readObject();
            return apMessage;
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
