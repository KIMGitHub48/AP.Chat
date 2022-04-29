package apClientUI;

import apCommon.ApMessage;
import apCommon.apModuleServices.ClientService.ClientCoreService;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public class MainClientUIServiceImp implements MainClientUIService{
    private ClientCoreService clientCoreService = ClientCoreService.getFirst();
    private MainClientUI mainClientUI;

    //Метод возвращает первую имплиментацию этого интерфейса.
    static MainClientUIServiceImp getFirst() {
        List<MainClientUIServiceImp> mainClientUIServiceImpList = ServiceLoader
                .load(MainClientUIServiceImp.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
        return mainClientUIServiceImpList.get(0);
    }

    @Override
    public void SetMainClientUI(MainClientUI mainClientUI) {
        this.mainClientUI = mainClientUI;
    }//TODO Ненужный метод

    @Override
    public void Launcher(String[] args) {
        mainClientUI = new MainClientUI();
        mainClientUI.Launcher(args);
//        mainClientUI.Launcher(args);
    }

    @Override
    public boolean IsConnected() {
        return clientCoreService.IsConnected();
    }

    @Override
    public void ConnectToServer() {
        Runnable mainRunnable = () -> {
            clientCoreService.ConnectToServer();
        };
        new Thread(mainRunnable, "ClientAuthorizationConnectToServer").start();
    }

    @Override
    public boolean isAuthorizationAvailable() {
        return clientCoreService.isAuthorizationAvailable();
    }

    @Override
    public void AuthorizationButtonEnterInThread() {
        clientCoreService.AuthorizationButtonEnterInThread();
    }

    @Override
    public void setAuthorizationAvailable(boolean available) {
        clientCoreService.SetAuthorizationAvailable(available);
    }

    @Override
    public void SendMessage(ApMessage apMessage) {
        clientCoreService.SendMessage(apMessage);
    }
}
