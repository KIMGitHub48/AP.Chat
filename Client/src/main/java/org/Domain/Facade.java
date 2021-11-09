package org.Domain;


public class Facade {
    public static void SendMessageToServer(org.Domain.Net.Message message){
        Main.domainMainRef.SendMessage(message);
    }
    public static void ConnectToServer(){
        Main.domainMainRef.ConnectToServer();
    }

    public static void MessageFromServer(org.Domain.Net.Message message) {
        Main.domainMainRef.SortMessage(message);
    }
}
