package apClientUI;

import apCommon.ApMessage;

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
    // Метод устанавливает ссылку на объект MainClientUI.
    void SetMainClientUI(MainClientUI mainClientUI);
    // Запускает UI.
    void Launcher(String[] args);

    boolean IsConnected();

    void ConnectToServer();

    boolean isAuthorizationAvailable();

    void AuthorizationButtonEnterInThread();

    void setAuthorizationAvailable(boolean available);

    void SendMessage(ApMessage apMessage);
}
