package apClientCore.Core;
/*
    Точка входа в приложение.
 */


import apClientCore.Core.Net.ConnectToServer;
import apClientCore.Core.Net.SortOutMessageInThread;
import apClientCore.Core.ServiceImplementation.ClientCoreServiceImp;
import apClientCore.Core.ServiceImplementation.MainClientCoreServiceImp;
import apCommon.ApMessage;
import apCommon.apModuleServices.ClientUIService;
//import apAuthorization.Test;

import java.net.Socket;
import java.util.ArrayList;

public class MainClientCore {
    //public static MainClientDomain mainDomainRef;
    private ConnectToServer connectToServer;
    private String login;
    private String password;
    private boolean isAuthorizationAvailable;//Поле регулирует доступность авторизации для клиента, если false то ответ от сервера для авторизации будет игнорироваться
    private ArrayList<ApMessage> presentationApMessageList = new ArrayList<>(); //Лист синхронизирован, использовать нельзя, использовать только в методе GetSetPresentationApMessageFromList
    //    private apCommon.apModuleServices.ClientAuthorizationService clientAuthorizationService = ClientAuthorizationService.getFirst();
//    private apCommon.apModuleServices.ClientOptionsService clientOptionsService = ClientOptionsService.getFirst();
    private apCommon.apModuleServices.ClientUIService clientUIService = ClientUIService.getFirst();


    public MainClientCore() {
        System.out.println("Запущен Клиент");
        //mainDomainRef = this;
        //FacadeClientPresentation.Launcher(args);
        //clientAuthorizationService.launcher(args);
        //clientOptionsService.launcher(args);
        MainClientCoreServiceImp.mainClientCore = this;
        ClientCoreServiceImp.mainClientCore = this;
        isAuthorizationAvailable = false;
    }

    public void Launcher(String[] args) {
        clientUIService.Launcher(args);
    }

    //Используется в 2 интерфейсах!
    public boolean isAuthorizationAvailable() {
        return isAuthorizationAvailable;
    }

    public void AuthorizationButtonEnterInThread() {

    }

    public void setAuthorizationAvailable(boolean authorizationAvailable) {
        isAuthorizationAvailable = authorizationAvailable;
    }

    public void SendMessage(ApMessage apMessage) {
        SortOutMessageInThread sortOutMessageInThread = new SortOutMessageInThread(apMessage);
        sortOutMessageInThread.start();
    }

    public void SetLogin(String login) {
        this.login = login;
    }

    public void SetPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Socket GetServerSocket() {
        if (IsConnected()) {
            Socket serverSocket = connectToServer.GetServerSocket();
            return serverSocket;
        } else {
            return null;
        }
    }

    public boolean IsConnected() {
        if ((connectToServer != null) && (connectToServer.IsConnected())) {
            return true;
        } else {
            return false;
        }
    }

    public void ConnectToServer() {
        System.out.println("Инициирован Connect");
        String IP = clientUIService.GetServerIP();
        Integer Port = clientUIService.GetServerPort();
        if (connectToServer == null) {
            connectToServer = new ConnectToServer();
            connectToServer.ConnectToServer(IP, Port);
        } else {
            connectToServer.ConnectToServer(IP, Port);
        }
    }

//    public void SendMessageToServer(ApMessage apMessage) {
//        if (IsConnected()) {
//            Socket serverSocket = connectToServer.GetServerSocket();
//            SendMessageInThread sendMessageThread = new SendMessageInThread(serverSocket, apMessage);
//            sendMessageThread.start();
//        }
//    }

    public void SortOutMessageInThread(ApMessage apMessage) {
        SortOutMessageInThread sortOutMessageInThread = new SortOutMessageInThread(apMessage);
        sortOutMessageInThread.start();
    }

//    public void WaitingAuthorizationResponse() {
//        WaitingAuthorizationResponse waitingAuthorizationResponse = new WaitingAuthorizationResponse();
//        waitingAuthorizationResponse.waitResponse();
//    }
}
