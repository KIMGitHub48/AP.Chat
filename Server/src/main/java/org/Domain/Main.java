package org.Domain;
/*
    Точка входа в приложение.
 */

import java.net.Socket;
import java.util.ArrayList;

public class Main {
    private Main (){
        mainRef = this;
    }

    public static Main mainRef ;
    private static org.Domain.Net.Server server;

    public static void main(String[] args) {
        org.Presentation.Facade.Launcher(args);//Запускает UI в новом потоке.
    }

    public ArrayList<Thread> getArrayListClientThread() {
        return server.getArrayListClientThread();
    }
    public ArrayList<Socket> getArrayListClientSocket() {
        return server.getArrayListClientSocket();
    }

    public void StartServer(){
        server = new org.Domain.Net.Server(4848);
        server.Start();//Стартует сетевую часть сервер в новом потоке.
    }
}