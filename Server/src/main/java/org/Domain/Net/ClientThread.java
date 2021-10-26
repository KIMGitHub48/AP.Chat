package org.Domain.Net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket clientSocket;
    private BufferedReader clientSockedStreamIn; // поток чтения из сокета
    private Thread threadIn;
    private boolean threadInStartFlag;
    ClientThread(Socket socket){
        clientSocket = socket;
    }

    @Override
    public void run() {
        SocketThreadInStart();
    }

    private void SocketThreadInStart(){
        org.Domain.Net.Message message;
        threadInStartFlag=true;
        while (threadInStartFlag) {
            message = SocketStreamIn();
            if (message != null){
                //В случае если пришел объект сообщения
            }
        }
    }

    private org.Domain.Net.Message SocketStreamIn(){
        org.Domain.Net.Message message;
        try {
            message = (org.Domain.Net.Message) ClientObjectInputStream().readObject();
            return message;
        } catch (ClassNotFoundException | IOException e) {
            return null;
        }
    }

    private ObjectInputStream ClientObjectInputStream() {
        try {
            ObjectInputStream  clientObjectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            return clientObjectInputStream;
        } catch (IOException e) {
            return null;
        }
    }

    public boolean AllStop(){
        try {
            clientSocket.shutdownInput();
            threadInStartFlag=false;
//            threadIn.interrupt();
            return true;
        } catch (IOException e) {
            return false;
        }
    } //Останавливает StreamIn, threadIn

    public void test(){

    }
}
