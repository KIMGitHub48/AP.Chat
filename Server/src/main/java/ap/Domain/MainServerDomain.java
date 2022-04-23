package ap.Domain;
/*
    Точка входа в приложение.
 */

import apCommon.*;
//import ap.Domain.Net.OutMessages.chatChannelMassageInThread;
import ap.Domain.Net.Server;
import ap.Presentation.FacadeServerPresentation;

import java.net.Socket;
import java.util.ArrayList;

public class MainServerDomain {
    public static MainServerDomain mainServerDomainRef;
    private static Server server;
    ActualLoginsInfo actualLoginsInfo = new ActualLoginsInfo();

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

//    public void SortMessage(ApMessage apMessage) {
//        ApMessageEnumType type = apMessage.getType();
//        switch (type) {
//            case chatChannelText:
//                ChatMessageToAll(apMessage);
//                break;
//            default:
//                System.out.println("ID отправляемого сообщения не найдено, ID:"+type);
//        }
//    }

//    public void ChatMessageToAll(ApMessage apMessage) {
//        Thread chatMessageToAllInThread = new chatChannelMassageInThread(apMessage);
//        chatMessageToAllInThread.start();
//    }

    public ArrayList<Socket> getArrayListClientSocket() {
        return server.getArrayListClientSocket();
    }

    public void AddActualLoginInfo(String login, Socket socket){
        actualLoginsInfo.AddActualInfo(login,socket);
    }

    public ArrayList<Socket> CompareActualLoginsWithMeta(ArrayList<String> loginsFromMeta){
        return actualLoginsInfo.CompareActualLoginsWithMeta(loginsFromMeta);
    }
}