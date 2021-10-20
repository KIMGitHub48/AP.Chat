package org.Domain;
/*
    Точка входа в приложение.
 */


public class Main {
    public static void main(String[] args) {
        org.Presentation.Facade.Launcher(args);//Запускает UI в новом потоке.

        org.Domain.Net.Server server = new org.Domain.Net.Server();
        server.Start();//Стартует сетевую часть сервера.
    }
}