package apCommon.apModuleServices;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public interface ClientUIService {
    //Метод запускает UI модуля в отдельном потоке
    //void launcher(String[] args);
    void main(String[] args);

    //Метод возвращает первую имплиментацию этого интерфейса.
    static ClientUIService getFirst() {
        List<ClientUIService> clientUIServiceList = ServiceLoader
                .load(ClientUIService.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
        return clientUIServiceList.get(0);
    }
}
