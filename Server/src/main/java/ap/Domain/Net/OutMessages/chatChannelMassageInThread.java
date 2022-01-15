package ap.Domain.Net.OutMessages;

import ap.DATA.ApacheDerby.ApacheDerby;
import ap.common.*;
import ap.Domain.MainServerDomain;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class chatChannelMassageInThread extends Thread {
    private ApMessage message;

    public chatChannelMassageInThread(ApMessage outMessage) {
        message = outMessage;
    }

    @Override
    public void run() {
            if (ApacheDerby.checkRecord(message.getChatChannelText())) {          // Если сообщение записано в базе данных, рассылаем его остальным
                System.out.println("Запущен перебор сокетов");
                ArrayList<Socket> arrayListClientSocket = MainServerDomain.mainServerDomainRef.getArrayListClientSocket();
                for (Socket element : arrayListClientSocket) {
                    SendMessage(element);
                    System.out.println("Элементы:"+element);
                }
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