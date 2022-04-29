package apCommon.apModuleServices.ClientService;

import apCommon.ApMessage;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public interface ClientCoreService {
    //Метод возвращает первую имплиментацию этого интерфейса.
    static ClientCoreService getFirst() {
        List<ClientCoreService> clientCoreServiceList = ServiceLoader
                .load(ClientCoreService.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
        return clientCoreServiceList.get(0);
    }

    //Метод возвращает состояние подключения
    boolean IsConnected();

    //Метод Инициирует соединение
    void ConnectToServer();

    void SetLogin(String login);

    void SetPassword(String password);

    //Проверяет доступна ли авторизация;
    boolean isAuthorizationAvailable();

    void AuthorizationButtonEnterInThread();

    void SetAuthorizationAvailable(boolean authorizationAvailable);

    void SendMessage(ApMessage apMessage);
}
