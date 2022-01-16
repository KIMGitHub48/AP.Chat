package ap.Domain;
/*
    Точка входа в приложение.
 */

import ap.Domain.Net.ConnectToServer;
import ap.common.*;
import ap.Domain.Net.InMessages.ChatChannelText;
import ap.Domain.Net.SendMessageInThread;
import ap.Presentation.FacadeClientPresentation;

import java.net.Socket;

public class MainClientDomain {
    public static MainClientDomain mainDomainRef;
    private ConnectToServer connectToServer;
    private String login;
    private String password;

    MainClientDomain(String[] args) {
        System.out.println("Запущен Клиент");
        mainDomainRef = this;
        FacadeClientPresentation.Launcher(args);
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
        Socket serverSocket = connectToServer.GetServerSocket();
        SendMessageInThread sendMessageThread = new SendMessageInThread(serverSocket, apMessage);
        sendMessageThread.start();
    }

    public void SortMessage(ApMessage apMessage) {
        ApMessageEnumType type = apMessage.getType();
        switch (type) {
            case chatChannelText:
                ChatChannelText chatChannelText = new ChatChannelText(apMessage);
                chatChannelText.Process();
                break;
            case authorization:
                break;
            default:
                System.out.println("type отправляемого сообщения не распознано");
        }
    }

    public boolean IsConnected(){
        if ((connectToServer != null) && (connectToServer.IsConnected())){
            return true;
        } else {
            return false;
        }
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
}
