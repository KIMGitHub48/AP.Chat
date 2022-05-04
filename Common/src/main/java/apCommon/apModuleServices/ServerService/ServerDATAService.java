package apCommon.apModuleServices.ServerService;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public interface ServerDATAService {
    //Метод возвращает первую имплиментацию этого интерфейса.
    static ServerDATAService getFirst() {
        List<ServerDATAService> serverDATAServiceList = ServiceLoader
                .load(ServerDATAService.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
        return serverDATAServiceList.get(0);

        //Тестовый коммент Артамонов С.С.
    }
    //Комментарий Илья 1


    //Коментарий Илья 2

    //Ком 3
}
