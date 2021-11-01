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

    public static void addSystemMessage(String text){
        Main.presentationMain.SetSystemMessage(text);
    }
}
