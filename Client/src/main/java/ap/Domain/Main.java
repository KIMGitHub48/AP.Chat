package ap.Domain;
/*
    Точка входа в приложение.
 */

import ap.Domain.Net.ConnectToServer;
import ap.Domain.Net.Message;
import ap.Domain.Net.Messages.In.ChatChannelText;
import ap.Domain.Net.Messages.Out.SendMessageInThread;
import ap.Presentation.Facade;

import java.net.Socket;

public class Main {
    public static Main domainMainRef;
    private ConnectToServer connectToServer;

    Main(String[] args){
        System.out.println("Запущен Клиент");
        domainMainRef = this;
        Facade.Launcher(args);
    }

    public void ConnectToServer(){
        connectToServer = new ConnectToServer();
        connectToServer.ConnectToServer();
    }

    public void SendMessage(Message message){
        Socket serverSocket = connectToServer.GetServerSocket();
        SendMessageInThread sendMessageThread = new SendMessageInThread(serverSocket ,message);
        sendMessageThread.start();
    }

    public void SortMessage(Message message) {
        String id = message.getId();
        switch (id) {
            case "chatChannelText":
                ChatChannelText chatChannelText = new ChatChannelText(message);
                chatChannelText.Process();
                break;
            default:
                System.out.println("ID отправляемого сообщения не найдено");
        }
    }
}
