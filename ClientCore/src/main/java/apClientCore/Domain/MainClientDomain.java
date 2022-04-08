package apClientCore.Domain;
/*
    Точка входа в приложение.
 */


import apClientCore.Domain.Net.InMessages.Authorization.WaitingAuthorizationResponse;
import apClientCore.Domain.Net.ConnectToServer;
import apClientCore.Domain.Net.SortOutMessageInThread;
import apClientCore.Presentation.MainClientPresentation;
import apClientCore.Presentation.FacadeClientPresentation;
import apCommon.ApMessage;
import apCommon.apModuleServices.ClientAuthorizationService;
import apCommon.apModuleServices.ClientOptionsService;
//import apAuthorization.Test;

import java.net.Socket;
import java.util.ArrayList;

public class MainClientDomain implements MainClientCoreService {
    //public static MainClientDomain mainDomainRef;
    private ConnectToServer connectToServer;
    private String login;
    private String password;
    private boolean IsAuthorizationAvailable;//Поле регулирует доступность авторизации для клиента, если false то ответ от сервера для авторизации будет игнорироваться
    private ArrayList<ApMessage> presentationApMessageList = new ArrayList<>(); //Лист синхронизирован, использовать нельзя, использовать только в методе GetSetPresentationApMessageFromList
    private apCommon.apModuleServices.ClientAuthorizationService clientAuthorizationService = ClientAuthorizationService.getFirst();
    private apCommon.apModuleServices.ClientOptionsService clientOptionsService = ClientOptionsService.getFirst();


    MainClientDomain(String[] args) {
        System.out.println("Запущен Клиент");
        //mainDomainRef = this;
        //FacadeClientPresentation.Launcher(args);
        clientAuthorizationService.launcher(args);
        clientOptionsService.launcher(args);
        IsAuthorizationAvailable = false;
    }

    //region !! Имплементация MainClientCoreService
    public boolean isAuthorizationAvailable() {
        return IsAuthorizationAvailable;
    }

    @Override
    public void setAuthorizationAvailable(boolean authorizationAvailable) {
        IsAuthorizationAvailable = authorizationAvailable;
    }

    @Override
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Socket GetServerSocket(){
        if (IsConnected()) {
            Socket serverSocket = connectToServer.GetServerSocket();
            return serverSocket;
        } else {
            return null;
        }
    }
    @Override
    public boolean IsConnected(){
        if ((connectToServer != null) && (connectToServer.IsConnected())){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void ConnectToServer() {
        String IP = clientOptionsService.GetServerIP();
        Integer Port = clientOptionsService.GetServerPort();
        if (connectToServer == null) {
            connectToServer = new ConnectToServer();
            connectToServer.ConnectToServer(IP,Port);
        } else {
            connectToServer.ConnectToServer(IP,Port);
        }
    }
    //endregion

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

    public void WaitingAuthorizationResponse() {
        WaitingAuthorizationResponse waitingAuthorizationResponse = new WaitingAuthorizationResponse();
        waitingAuthorizationResponse.waitResponse();
    }
}
