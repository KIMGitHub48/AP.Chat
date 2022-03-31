package apCommon.apModuleServices;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public interface ClientAuthorizationService {
    //Метод запускает UI модуля в отдельном потоке
    void launcher(String[] args);

    //Метод возвращает первую имплиментацию этого интерфейса.
    static ClientAuthorizationService getFirst() {
        List<ClientAuthorizationService> clientAuthorizationServiceList = ServiceLoader
                .load(ClientAuthorizationService.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
        return clientAuthorizationServiceList.get(0);
    }
}
