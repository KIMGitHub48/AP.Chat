package ap.Domain;

import apCommon.*;

public class FacadeServerDomain {
//    public static void SendMessage(ApMessage apMessage) {
//        MainServerDomain.mainServerDomainRef.SortMessage(apMessage);
//    }

//    public static void chatMessageToAll(org.Domain.Net.Message message){
//        Main.domainMainRef.chatMessageToAll(message);
//    }

    public static void StartServer(){
        MainServerDomain.mainServerDomainRef.StartServer();
    }
    public static void StopServer(){
        MainServerDomain.mainServerDomainRef.StopServer();
    }
}
