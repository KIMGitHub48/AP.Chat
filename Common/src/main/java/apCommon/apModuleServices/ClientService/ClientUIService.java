package apCommon.apModuleServices.ClientService;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public interface ClientUIService {
    //Метод запускает UI модуля в отдельном потоке
    //void launcher(String[] args);
    void Launcher(String[] args);

    //Метод возвращает первую имплиментацию этого интерфейса.
    static ClientUIService getFirst() {
        List<ClientUIService> clientUIServiceList = ServiceLoader
                .load(ClientUIService.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
        return clientUIServiceList.get(0);
    }

    void AuthorizationResponseActionConnectionError();

    void AuthorizationResponseActionTimeError();

    void AuthorizationResponseActionNotPassed();

    void AuthorizationResponseActionPassed();

    String GetServerIP();

    Integer GetServerPort();
}
