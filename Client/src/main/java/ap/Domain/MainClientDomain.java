package ap.Domain;
/*
    Точка входа в приложение.
 */

import ap.Domain.Net.ConnectToServer;
import ap.common.*;
import ap.Domain.Net.InMessages.ChatChannelText;
import ap.Domain.Net.OutMessages.SendMessageInThread;
import ap.Presentation.FacadeClientPresentation;

import java.net.Socket;

public class MainClientDomain {
    public static MainClientDomain mainDomainRef;
    private ConnectToServer connectToServer;

    MainClientDomain(String[] args) {
        System.out.println("Запущен Клиент");
        mainDomainRef = this;
        FacadeClientPresentation.Launcher(args);
    }

    public void ConnectToServer() {
        connectToServer = new ConnectToServer();
        connectToServer.ConnectToServer();
    }

    public void SendMessage(ApMessage apMessage) {
        Socket serverSocket = connectToServer.GetServerSocket();
        SendMessageInThread sendMessageThread = new SendMessageInThread(serverSocket, apMessage);
        sendMessageThread.start();
    }

    public void SortMessage(ApMessage apMessage) {
        ApMessageEnumType type = apMessage.getType();
        switch (type) {
            case chatChannelText:
                ChatChannelText chatChannelText = new ChatChannelText(apMessage);
                chatChannelText.Process();
                break;
            case authorization:
                break;
            default:
                System.out.println("type отправляемого сообщения не распознано");
        }
    }
}
