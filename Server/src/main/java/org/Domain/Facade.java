package org.Domain;

public class Facade {
    public static void SendMessage(org.Domain.Net.Message message) {
        Main.domainMainRef.SortMessage(message);
    }

//    public static void chatMessageToAll(org.Domain.Net.Message message){
//        Main.domainMainRef.chatMessageToAll(message);
//    }

    public static void StartServer(){
        Main.domainMainRef.StartServer();
    }
    public static void StopServer(){
        Main.domainMainRef.StopServer();
    }
}
