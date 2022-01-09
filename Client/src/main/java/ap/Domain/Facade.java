package ap.Domain;


import ap.Domain.Net.Message;

public class Facade {
    public static void SendMessageToServer(Message message){
        Main.domainMainRef.SendMessage(message);
    }
    public static void ConnectToServer(){
        Main.domainMainRef.ConnectToServer();
    }

    public static void MessageFromServer(Message message) {
        Main.domainMainRef.SortMessage(message);
    }
    //gfgdfgdfrgrd
}
