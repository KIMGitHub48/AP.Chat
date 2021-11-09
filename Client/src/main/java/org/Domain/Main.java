package org.Domain;
/*
    Точка входа в приложение.
 */

public class Main {
    public static Main domainMainRef;
    private org.Domain.Net.ConnectToServer connectToServer;

    Main(String[] args){
        domainMainRef = this;
        org.Presentation.Facade.Launcher(args);
    }

    public void ConnectToServer(){
        connectToServer = new org.Domain.Net.ConnectToServer();
        connectToServer.ConnectToServer();
    }

    public void SendMessage(){
//        Socket serverSocket = connectToServer.GetServerSocket();
//        org.Domain.Net.Messages.Out.SendMessageInThread sendMessageThread = new org.Domain.Net.Messages.Out.SendMessageInThread(serverSocket ,message);
//        sendMessageThread.start();
    }

//    public void SendMessage(org.Domain.Net.Message message){
//        Socket serverSocket = connectToServer.GetServerSocket();
//        org.Domain.Net.Messages.Out.SendMessageInThread sendMessageThread = new org.Domain.Net.Messages.Out.SendMessageInThread(serverSocket ,message);
//        sendMessageThread.start();
//    }

    public void SortMessage(org.Domain.Net.Message message) {
        String id = message.getId();
        switch (id) {
            case ("toChatChannel"):
                org.Domain.Net.Messages.In.ToChannelMessage toChannelMessage = new org.Domain.Net.Messages.In.ToChannelMessage(message);
                toChannelMessage.Process();
            default:
                System.out.println("ID отправляемого сообщения не найдено");
        }
    }
}
