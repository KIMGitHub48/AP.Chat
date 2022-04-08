package apClientUI;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public interface MainClientUIService {
    //Метод возвращает первую имплиментацию этого интерфейса.
    static MainClientUIService getFirst() {
        List<MainClientUIService> mainClientUIServiceList = ServiceLoader
                .load(MainClientUIService.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
        return mainClientUIServiceList.get(0);
    }

    boolean IsConnected();

    void ConnectToServer();
}
