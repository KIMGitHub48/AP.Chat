package apCommon.apModuleServices;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public interface ClientOptionsService {
    //Метод запускает UI модуля в отдельном потоке
    void launcher(String[] args);

    //Метод возвращает первую имплиментацию этого интерфейса.
    static ClientOptionsService getFirst() {
        List<ClientOptionsService> clientOptionsServiceList = ServiceLoader
                .load(ClientOptionsService.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
        return clientOptionsServiceList.get(0);
    }

    //Показывает цену Options
    void ShowOptionsStage();

    //Возвращает IP для подключения к серверу
    String GetServerIP();

    //Возвращает Port для подключения к серверу
    Integer GetServerPort();
}
