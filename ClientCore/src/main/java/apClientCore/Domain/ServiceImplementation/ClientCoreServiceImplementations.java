package apClientCore.Domain.ServiceImplementation;

import apClientCore.Domain.MainClientCoreService;
import apClientCore.Domain.Net.SortOutMessageInThread;
import apCommon.ApMessage;
import apCommon.apModuleServices.ClientCoreService;
import apClientCore.Domain.MainClientDomain;

public class ClientCoreServiceImplementations implements ClientCoreService {
    private MainClientCoreService mainClientCoreService = MainClientCoreService.getFirst();

    @Override
    public boolean IsConnected() {
        boolean isConnected = mainClientCoreService.IsConnected();
        return isConnected;
    }

    @Override
    public void ConnectToServer() {
        mainClientCoreService.ConnectToServer();
    }

    @Override
    public void SortOutMessageInThread(ApMessage apMessage) {
        apClientCore.Domain.Net.SortOutMessageInThread sortOutMessageInThread = new SortOutMessageInThread(apMessage);
        sortOutMessageInThread.start();
    }

    @Override
    public void setLogin(String login) {
        mainClientCoreService.setLogin(login);
    }

    @Override
    public void setPassword(String password) {
        mainClientCoreService.setPassword(password);
    }

    @Override
    public void ShowHideChatStage(boolean show) {
        //TODO Создать.
    }
}
