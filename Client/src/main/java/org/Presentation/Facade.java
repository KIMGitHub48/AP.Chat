package org.Presentation;
/*
Этот класс реализует паттерн программирования Фасад, все общение между этим пакетом и остальными должно быть реализованно через этот класс.
 */


public class Facade {
    public static void Launcher(String[] args){
        Runnable mainRunnable = () -> {
            Main.main(args);
        };
        new Thread(mainRunnable, "UIThread").start();
    }//Запускает UI в новом потоке.
    public static void AddSystemMessage(String text){
        Main.presentationMainRef.AddSystemMessage(text);
    }
    public static String GetIPFromTextField(){
        return Main.presentationMainRef.GetIPFromTextField();
    }
    public static Integer GetPortFromTextField(){
        return Main.presentationMainRef.GetPortFromTextField();
    }
    public static void MessageFromServer(org.Domain.Net.Message message) {

    }

    public static void chatChannelMessage(String channelMessage, String channelName) {
        Main.presentationMainRef.chatChannelMessage(channelMessage, channelName);
    }
}
