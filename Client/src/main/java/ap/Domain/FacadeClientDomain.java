package ap.Domain;


import ap.Domain.Net.Message;

public class FacadeClientDomain {
    public static void SendMessageToServer(Message message){
        MainClientDomain.mainDomainRef.SendMessage(message);
    }
    public static void ConnectToServer(){
        MainClientDomain.mainDomainRef.ConnectToServer();
    }

    public static void MessageFromServer(Message message) {
        MainClientDomain.mainDomainRef.SortMessage(message);
    }
    //gfgdfgdfrgrd
}
