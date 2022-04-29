package apClientCore.DATA;

import apClientCore.Core.MainClientCoreService;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public interface DATAService {
    //Метод возвращает первую имплиментацию этого интерфейса.
    static DATAService getFirst() {
        List<DATAService> DATAServiceList = ServiceLoader
                .load(DATAService.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
        return DATAServiceList.get(0);
    }
}
