package apServerCore.DATA;

import apCommon.ApMessage;
import apCommon.ApMetaMessage;

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

    //Это будет основной метод для общения с базой.
    //Метод должен принять сообщение обработать и создать ApMetaMessage, заполнить его и вернуть.
    ApMetaMessage MessageToData(ApMessage apMessage);
}
