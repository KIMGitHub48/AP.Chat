package ap.Domain;


import ap.common.*;

public class FacadeClientDomain {
    public static void SendMessageToServer(ApMessage apMessage){
        MainClientDomain.mainDomainRef.SendMessage(apMessage);
    }
    public static void ConnectToServer(String IP, Integer Port){
        MainClientDomain.mainDomainRef.ConnectToServer(IP,Port);
    }

    public static void MessageFromServer(ApMessage apMessage) {
        MainClientDomain.mainDomainRef.SortMessage(apMessage);
    }

    public static boolean IsConnected(){return MainClientDomain.mainDomainRef.IsConnected();}
}
