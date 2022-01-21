package ap.Domain;


import ap.common.*;

public class FacadeClientDomain {
    public static void SendMessageToServer(ApMessage apMessage){
        MainClientDomain.mainDomainRef.SendMessage(apMessage);
    }
    public static void ConnectToServer(String IP, Integer Port){
        MainClientDomain.mainDomainRef.ConnectToServer(IP,Port);
    }

    public static void SortMessageInNewThread(ApMessage apMessage) {
        MainClientDomain.mainDomainRef.SortMessageInNewThread(apMessage);
    }

    public static boolean IsConnected(){return MainClientDomain.mainDomainRef.IsConnected();}
}
