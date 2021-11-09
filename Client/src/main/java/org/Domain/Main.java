package org.Domain;
/*
    Точка входа в приложение.
 */

import org.Domain.Net.Messages.In.ChatChannelText;

import java.net.Socket;

public class Main {
    public static Main domainMainRef;
    private org.Domain.Net.ConnectToServer connectToServer;

    Main(String[] args){
        System.out.println("Запущен Клиент ");
        domainMainRef = this;
        org.Presentation.Facade.Launcher(args);
    }

    public void ConnectToServer(){
        connectToServer = new org.Domain.Net.ConnectToServer();
        connectToServer.ConnectToServer();
    }

    public void SendMessage(org.Domain.Net.Message message){
        Socket serverSocket = connectToServer.GetServerSocket();
        org.Domain.Net.Messages.Out.SendMessageInThread sendMessageThread = new org.Domain.Net.Messages.Out.SendMessageInThread(serverSocket ,message);
        sendMessageThread.start();
    }

    public void SortMessage(org.Domain.Net.Message message) {
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
