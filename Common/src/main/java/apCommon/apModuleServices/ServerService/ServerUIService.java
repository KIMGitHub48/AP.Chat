package apCommon.apModuleServices.ServerService;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public interface ServerUIService {
    //Метод запускает UI модуля в отдельном потоке
    //void launcher(String[] args);
    void Launcher(String[] args);
    //Метод возвращает первую имплиментацию этого интерфейса.
    static ServerUIService getFirst() {
        List<ServerUIService> serverUIServiceList = ServiceLoader
                .load(ServerUIService.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
        return serverUIServiceList.get(0);
    }

    Integer GetServerPort();
}
