package apCommon.apModuleServices;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

//Этот сервис служит переадрисацией на друшие сервисы
public interface ClientCoreMapService {
    //Метод возвращает первую имплиментацию этого интерфейса.
    static ClientCoreMapService getFirst() {
        List<ClientCoreMapService> clientCoreMapServiceList = ServiceLoader
                .load(ClientCoreMapService.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
        return clientCoreMapServiceList.get(0);
    }

    //Показывает Options Stage
    void ShowOptionsStage();
}
