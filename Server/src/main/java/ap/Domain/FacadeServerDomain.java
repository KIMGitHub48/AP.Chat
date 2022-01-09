package ap.Domain;

import ap.Domain.Net.Message;

public class FacadeServerDomain {
    public static void SendMessage(Message message) {
        MainServerDomain.mainServerDomainRef.SortMessage(message);
    }

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
