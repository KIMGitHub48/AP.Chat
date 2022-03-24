package apClient.Domain;
/*
    Точка входа в приложение.
 */

import apClient.Domain.Net.InMessages.Authorization.WaitingAuthorizationResponse;
import apClient.Domain.Net.ConnectToServer;
import apClient.Domain.Net.SortOutMessageInThread;
import apClient.Presentation.MainClientPresentation;
import apCommon.*;
import apClient.Presentation.FacadeClientPresentation;
import apCommon.ApMessage;

import java.net.Socket;
import java.util.ArrayList;

public class MainClientDomain {
    public static MainClientDomain mainDomainRef;
    private ConnectToServer connectToServer;
    private String login;
    private String password;
    private boolean IsAuthorizationAvailable;//Поле регулирует доступность авторизации для клиента, если false то ответ от сервера для авторизации будет игнорироваться
    private ArrayList<ApMessage> presentationApMessageList = new ArrayList<>(); //Лист синхронизирован, использовать нельзя, использовать только в методе GetSetPresentationApMessageFromList

    MainClientDomain(String[] args) {
        System.out.println("Запущен Клиент");
        mainDomainRef = this;
        FacadeClientPresentation.Launcher(args);
        IsAuthorizationAvailable = false;
    }

    public void ConnectToServer() {
        String IP = MainClientPresentation.mainPresentationRef.GetIPFromTextField();
        Integer Port = MainClientPresentation.mainPresentationRef.GetPortFromTextField();
        if (connectToServer == null) {
            connectToServer = new ConnectToServer();
            connectToServer.ConnectToServer(IP,Port);
        } else {
            connectToServer.ConnectToServer(IP,Port);
        }
    }

//    public void SendMessageToServer(ApMessage apMessage) {
//        if (IsConnected()) {
//            Socket serverSocket = connectToServer.GetServerSocket();
//            SendMessageInThread sendMessageThread = new SendMessageInThread(serverSocket, apMessage);
//            sendMessageThread.start();
//        }
//    }

    public Socket GetServerSocket(){
        if (IsConnected()) {
            Socket serverSocket = connectToServer.GetServerSocket();
            return serverSocket;
        } else {
            return null;
        }
    }

    public boolean IsConnected(){
        if ((connectToServer != null) && (connectToServer.IsConnected())){
            return true;
        } else {
            return false;
        }
    }

    public void SortOutMessageInThread(ApMessage apMessage) {
        SortOutMessageInThread sortOutMessageInThread = new SortOutMessageInThread(apMessage);
        sortOutMessageInThread.start();
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAuthorizationAvailable() {
        return IsAuthorizationAvailable;
    }

    public void setAuthorizationAvailable(boolean authorizationAvailable) {
        IsAuthorizationAvailable = authorizationAvailable;
    }

    public void WaitingAuthorizationResponse() {
        WaitingAuthorizationResponse waitingAuthorizationResponse = new WaitingAuthorizationResponse();
        waitingAuthorizationResponse.waitResponse();
    }
}
