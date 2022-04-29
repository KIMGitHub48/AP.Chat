package apCommon.apModuleServices.ServerService;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public interface ServerCoreService {
    //Метод возвращает первую имплиментацию этого интерфейса.
    static ServerCoreService getFirst() {
        List<ServerCoreService> serverCoreServiceList = ServiceLoader
                .load(ServerCoreService.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
        return serverCoreServiceList.get(0);
    }
}
