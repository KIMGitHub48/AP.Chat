package apClientAuthorization.ServiceImplemintation;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public interface ClientCore {
    //Метод показывает Options Stage
    void ShowOptionsStage();

    //Метод возвращает состояние подключения
    boolean IsConnected();

    //Метод Инициирует соединение
    boolean ConnectToServer();

    //Метод возвращает первую имплиментацию этого интерфейса.
    static ClientCore getFirst() {
        List<ClientCore> clientCoreList = ServiceLoader
                .load(ClientCore.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
        return clientCoreList.get(0);
    }

}
