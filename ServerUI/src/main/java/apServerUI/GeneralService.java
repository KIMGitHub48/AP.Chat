package apServerUI;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public interface GeneralService {
    //Метод возвращает первую имплиментацию этого интерфейса.
    static GeneralService getFirst() {
        List<GeneralService> generalServiceList = ServiceLoader
                .load(GeneralService.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
        return generalServiceList.get(0);
    }
    //Загружает сцену
    void LoadStage();

    //Показывает Options Stage
    void ShowGeneralStage();

    //Скрывает Options Stage
    void HideGeneralStage();

    //Возвращает Port для подключения к серверу
    Integer GetServerStartPort();

//    String GetLogin();
//
//    String GetPassword();
}
