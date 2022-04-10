package apClientCore.Core;

import java.net.Socket;
import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public interface MainClientCoreService {
    //Метод возвращает первую имплиментацию этого интерфейса.
    static MainClientCoreService getFirst() {
        List<MainClientCoreService> mainClientCoreServiceList = ServiceLoader
                .load(MainClientCoreService.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
        return mainClientCoreServiceList.get(0);
    }
    //Проверяет доступна ли авторизация;
    boolean isAuthorizationAvailable();

    //Делает процесс авторизации доступным
    void setAuthorizationAvailable(boolean authorizationAvailable);

    //Запоминает логин текущего авторизованного клиента.
    void setLogin(String login);

    //Запоминает пароль текущего авторищованного клиента.
    void setPassword(String password);

    //Возвращает логин текущего авторизованного клиента.
    String getLogin();

    //Возвращает пароль текущего авторищованного клиента.
    String getPassword();

    //Возвращает подключенный к серверу сокет.
    Socket GetServerSocket();

    //Возвращает статус подключения к серверу.
    boolean IsConnected();

    //Устанавливает подключение к серверу.
    void ConnectToServer();
}
