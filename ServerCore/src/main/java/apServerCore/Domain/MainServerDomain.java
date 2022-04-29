package apServerCore.Domain;
/*
    Точка входа в приложение.
 */

//import ap.Domain.Net.OutMessages.chatChannelMassageInThread;
import apServerCore.Domain.Net.Server.ServerStart;
import apServerCore.Presentation.FacadeServerPresentation;

import java.net.Socket;
import java.util.ArrayList;

public class MainServerDomain {
    public static MainServerDomain mainServerDomainRef;
    private static ServerStart serverStart;
    ActualLoginsInfo actualLoginsInfo = new ActualLoginsInfo();

    public MainServerDomain(String[] args) {
        System.out.println("Запущен Сервер");
        mainServerDomainRef = this;
        FacadeServerPresentation.Launcher(args);//Запускает UI в новом потоке.
    }

    public void StartServer() {
        serverStart = new ServerStart(4848);
        serverStart.Start();//Стартует сетевую часть сервер в новом потоке.
    }

    public void StopServer() {
        serverStart.Stop();
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
        return serverStart.getArrayListClientSocket();
    }

    public void AddActualLoginInfo(String login, Socket socket){
        actualLoginsInfo.AddActualInfo(login,socket);
    }

    public ArrayList<Socket> CompareActualLoginsWithMeta(ArrayList<String> loginsFromMeta){
        return actualLoginsInfo.CompareActualLoginsWithMeta(loginsFromMeta);
    }
}