package org.Domain1;
/*
    Точка входа в приложение.
 */

public class Main {
    public static Main domainMainRef;
    private org.Domain1.Net.ConnectToServer connectToServer;

    Main(String[] args){
        domainMainRef = this;
        org.Presentation.Facade.Launcher(args);
    }

    public void ConnectToServer(){
        connectToServer = new org.Domain1.Net.ConnectToServer();
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

    public void SortMessage(org.Domain1.Net.Message message) {
        String id = message.getId();
        switch (id) {
            case ("toChatChannel"):
                org.Domain1.Net.Messages.In.ToChannelMessage toChannelMessage = new org.Domain1.Net.Messages.In.ToChannelMessage(message);
                toChannelMessage.Process();
            default:
                System.out.println("ID отправляемого сообщения не найдено");
        }
    }
}
