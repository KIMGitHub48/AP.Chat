package apServerUI;

import apCommon.apModuleServices.ServerService.ServerUIService;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public interface MainServerUIService {
    //Метод запускает UI модуля в отдельном потоке
    //void launcher(String[] args);
    void Launcher(String[] args);
    //Метод возвращает первую имплиментацию этого интерфейса.
    static MainServerUIService getFirst() {
        List<MainServerUIService> mainServerUIServiceList = ServiceLoader
                .load(MainServerUIService.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
        return mainServerUIServiceList.get(0);
    }
}
