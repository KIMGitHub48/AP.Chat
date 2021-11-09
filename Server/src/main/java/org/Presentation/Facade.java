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
        Main.presentationMain.AddSystemMessage(text);
    }
    public static void AddTextToStartServerButtonAfterStart(){
        Main.presentationMain.AddTextToStartServerButtonAfterStart();
    }
    public static void AddTextToStartServerButtonAfterStop(){
        Main.presentationMain.AddTextToStartServerButtonAfterStop();
    }
}
