package ap.Domain;
/*
    Точка входа в приложение.
 */

import ap.Domain.Net.Message;
import ap.Domain.Net.Messages.chatChannelMassageInThread;
import ap.Domain.Net.Server;
import ap.Presentation.FacadeServerPresentation;

import java.net.Socket;
import java.util.ArrayList;

public class MainServerDomain {
    public static MainServerDomain mainServerDomainRef;
    private static Server server;

    public MainServerDomain(String[] args) {
        System.out.println("Запущен Сервер");
        mainServerDomainRef = this;
        FacadeServerPresentation.Launcher(args);//Запускает UI в новом потоке.
    }

    public void StartServer() {
        server = new Server(4848);
        server.Start();//Стартует сетевую часть сервер в новом потоке.
    }

    public void StopServer() {
        server.Stop();
    }

    public void SortMessage(Message message) {
        String id = message.getId();
        switch (id) {
            case "chatChannelText":
                ChatMessageToAll(message);
                break;
            default:
                System.out.println("ID отправляемого сообщения не найдено, ID:"+id);
        }
    }

    public void ChatMessageToAll(Message message) {
        Thread chatMessageToAllInThread = new chatChannelMassageInThread(message);
        chatMessageToAllInThread.start();
    }

    public ArrayList<Socket> getArrayListClientSocket() {
        return server.getArrayListClientSocket();
    }
}