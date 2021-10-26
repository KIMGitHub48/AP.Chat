package org.Domain;
/*
    Точка входа в приложение.
 */


import java.util.ArrayList;

public class Main {
    public static Main mainRef ;
    private static org.Domain.Net.Server server;
    Main (){
        mainRef = this;
    }
    public static void main(String[] args) {
        org.Presentation.Facade.Launcher(args);//Запускает UI в новом потоке.

        server = new org.Domain.Net.Server();
        server.Start();//Стартует сетевую часть сервер в новом потоке.
    }

    public ArrayList<Thread> getArrayListClientThread() {
        return server.getArrayListClientThread();
    }
}