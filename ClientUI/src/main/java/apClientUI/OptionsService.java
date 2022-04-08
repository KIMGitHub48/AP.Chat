package apClientUI;

import apCommon.apModuleServices.ClientOptionsService;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public interface OptionsService {
    //Метод возвращает первую имплиментацию этого интерфейса.
    static OptionsService getFirst() {
        List<OptionsService> optionsServiceList = ServiceLoader
                .load(OptionsService.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
        return optionsServiceList.get(0);
    }

    //Показывает цену Options
    void ShowOptionsStage();

    //Возвращает IP для подключения к серверу
    String GetServerIP();

    //Возвращает Port для подключения к серверу
    Integer GetServerPort();
}
