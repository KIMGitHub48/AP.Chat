package apCommon.apModuleServices;

import apCommon.ApMessage;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public interface ClientCoreService {
    //Метод показывает Options Stage
    void ShowOptionsStage();

    //Метод возвращает состояние подключения
    boolean IsConnected();

    //Метод Инициирует соединение
    void ConnectToServer();

    void SortOutMessageInThread(ApMessage apMessage);

    //Метод возвращает первую имплиментацию этого интерфейса.
    static ClientCoreService getFirst() {
        List<ClientCoreService> clientCoreServiceList = ServiceLoader
                .load(ClientCoreService.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
        return clientCoreServiceList.get(0);
    }

    void setLogin(String login);

    void setPassword(String password);

    void ShowHideChatStage(boolean b);
}
