package ap.Domain;
/*
    Точка входа в приложение.
 */

import ap.Domain.Net.ConnectToServer;
import ap.Domain.Net.OutMessages.AuthorizationOut;
import ap.common.*;
import ap.Domain.Net.InMessages.ChatChannelTextIn;
import ap.Domain.Net.SendMessageInThread;
import ap.Presentation.FacadeClientPresentation;

import java.net.Socket;
import java.util.ArrayList;

public class MainClientDomain {
    public static MainClientDomain mainDomainRef;
    private ConnectToServer connectToServer;
    private String login;
    private String password;
//    private boolean IsAuthorizationAvailable;//Поле регулирует доступность авторизации для клиента, если false то ответ от сервера для авторизации будет игнорироваться
//    private boolean IsAuthorizationPassed;
    private ArrayList<ApMessage> presentationApMessageList = new ArrayList<>(); //Лист синхронизирован, использовать нельзя, использовать только в методе GetSetPresentationApMessageFromList

    MainClientDomain(String[] args) {
        System.out.println("Запущен Клиент");
        mainDomainRef = this;
        FacadeClientPresentation.Launcher(args);
//        IsAuthorizationAvailable = true;
    }

    public void ConnectToServer(String IP, Integer Port) {
        if (connectToServer == null) {
            connectToServer = new ConnectToServer();
            connectToServer.ConnectToServer(IP,Port);
        } else {
            connectToServer.ConnectToServer(IP,Port);
        }
    }

    public void SendMessage(ApMessage apMessage) {
        if (IsConnected()) {
            Socket serverSocket = connectToServer.GetServerSocket();
            SendMessageInThread sendMessageThread = new SendMessageInThread(serverSocket, apMessage);
            sendMessageThread.start();
        }
    }

//    public void SortMessageInNewThread(ApMessage apMessage){
//            Runnable sortMessage = () -> {
//                SortMessage(apMessage);
//            };
//            new Thread(sortMessage).start();
//    }
//
//    private void SortMessage(ApMessage apMessage) {
//        ApMessageEnumType type = apMessage.getType();
//        switch (type) {
//            case chatChannelText:
//                ChatChannelTextIn chatChannelTextIn = new ChatChannelTextIn(apMessage);
//                chatChannelTextIn.Process();
//                break;
//            case authorization:
//                //AuthorizationResponse(apMessage);
//                break;
//            default:
//                System.out.println("type отправляемого сообщения не распознано");
//        }
//    }

//    private void AuthorizationResponse(ApMessage apMessage){
//        if (IsAuthorizationAvailable){
//            if(apMessage.getAuthorizationPassed()){
//                IsAuthorizationAvailable = false;
//                IsAuthorizationPassed = true;
//                login = apMessage.getLogin();
//                password = apMessage.getPassword();
//            }
//        }
//    }

//    public void ClearAuthorizationStatus(){
//        IsAuthorizationAvailable = true;
//        IsAuthorizationPassed = false;
//        login = null;
//        password = null;
//    }

    public boolean IsConnected(){
        if ((connectToServer != null) && (connectToServer.IsConnected())){
            return true;
        } else {
            return false;
        }
    }



//    public String getLogin() {
//        return login;
//    }
//
//    public void setLogin(String login) {
//        this.login = login;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

//    public boolean isAuthorizationAvailable() {
//        return IsAuthorizationAvailable;
//    }
//
//    public void setAuthorizationAvailable(boolean authorizationAvailable) {
//        IsAuthorizationAvailable = authorizationAvailable;
//    }
//
//    public boolean isAuthorizationPassed() {
//        return IsAuthorizationPassed;
//    }
//
//    public void setAuthorizationPassed(boolean authorizationPassed) {
//        IsAuthorizationPassed = authorizationPassed;
//    }

    public synchronized ApMessage GetOrAddPresentationApMessageList(ApMessage apMessage, boolean addApMessage) {
        if ((addApMessage) && (apMessage != null)){
            presentationApMessageList.add(apMessage);
            return null;
        } else {
            if (presentationApMessageList.isEmpty()) {
                return null;
            } else {
                ApMessage apMessageToReturn = presentationApMessageList.get(0);
                presentationApMessageList.remove(0);
                return apMessageToReturn;
            }
        }
    }

    public void AddApMessageToPresentationList(ApMessage apMessage){
        presentationApMessageList.add(apMessage);
    }

    public void SendAuthorizationMessage(String loginToDomainField, String passwordToDomainField) {
        AuthorizationOut authorizationOut = new AuthorizationOut(login,password);
        authorizationOut.Send();
    }
}
