package ap.Domain.Net;

import ap.common.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class WaitingMessageFromServerInThread extends Thread {
    private final Socket SERVER_SOCKET;
    private boolean threadInBreakFlag;

    WaitingMessageFromServerInThread(Socket socket) {
        SERVER_SOCKET = socket;
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
            SortInMessageInThread sortInMessageInThread = new SortInMessageInThread(apMessage);
            sortInMessageInThread.start();
        }
    }

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
            return new ObjectInputStream(SERVER_SOCKET.getInputStream());
        } catch (IOException e) {
            return null;
        }
    }
}
