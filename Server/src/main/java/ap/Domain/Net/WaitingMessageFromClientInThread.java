package ap.Domain.Net;

import apCommon.*;
import apCommon.ApMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class WaitingMessageFromClientInThread extends Thread {
    private final Socket socket;
    private boolean threadInBreakFlag;

    WaitingMessageFromClientInThread(Socket socket) {
        this.socket = socket;
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
            SortInMessageInThread sortInMessageInThread = new SortInMessageInThread(apMessage, socket);
            sortInMessageInThread.start();
        }
    }

    private ApMessage SocketStreamIn() {
        ApMessage message;
        try {
            message = (ApMessage) ClientObjectInputStream().readObject();
            return message;
        } catch (ClassNotFoundException | IOException e) {
            return null;
        }
    }

    private ObjectInputStream ClientObjectInputStream() {
        try {
            ObjectInputStream clientObjectInputStream = new ObjectInputStream(socket.getInputStream());
            return clientObjectInputStream;
        } catch (IOException e) {
            return null;
        }
    }

    public boolean stopClientThread() {
        try {
            socket.shutdownInput();
            threadInBreakFlag = false;
//            threadIn.interrupt();
            return true;
        } catch (IOException e) {
            return false;
        }
    } //Останавливает StreamIn, threadIn
}
