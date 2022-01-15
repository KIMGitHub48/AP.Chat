package ap.Domain;


import ap.common.*;

public class FacadeClientDomain {
    public static void SendMessageToServer(ApMessage apMessage){
        MainClientDomain.mainDomainRef.SendMessage(apMessage);
    }
    public static void ConnectToServer(){
        MainClientDomain.mainDomainRef.ConnectToServer();
    }

    public static void MessageFromServer(ApMessage apMessage) {
        MainClientDomain.mainDomainRef.SortMessage(apMessage);
    }
}
