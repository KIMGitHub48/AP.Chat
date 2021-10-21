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
//        Thread UIThread =new Thread(mainRunnable, "UIThread");
//        UIThread.start();
    }//Запускает UI в новом потоке.
}
