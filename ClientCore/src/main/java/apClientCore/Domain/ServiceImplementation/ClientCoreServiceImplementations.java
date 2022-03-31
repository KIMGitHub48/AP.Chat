package apClientCore.Domain.ServiceImplementation;

import apClientCore.Domain.Net.SortOutMessageInThread;
import apCommon.ApMessage;
import apCommon.apModuleServices.ClientCoreService;
import apClientCore.Domain.MainClientDomain;

public class ClientCoreServiceImplementations implements ClientCoreService {

    @Override
    public boolean IsConnected() {
        boolean isConnected = MainClientDomain.mainDomainRef.IsConnected();
        return isConnected;
    }

    @Override
    public void ConnectToServer() {
        MainClientDomain.mainDomainRef.ConnectToServer();
    }

    @Override
    public void SortOutMessageInThread(ApMessage apMessage) {
        apClientCore.Domain.Net.SortOutMessageInThread sortOutMessageInThread = new SortOutMessageInThread(apMessage);
        sortOutMessageInThread.start();
    }

    @Override
    public void setLogin(String login) {
        MainClientDomain.mainDomainRef.setLogin(login);
    }

    @Override
    public void setPassword(String password) {
        MainClientDomain.mainDomainRef.setPassword(password);
    }

    @Override
    public void ShowHideChatStage(boolean show) {
        //TODO Создать.
    }
}
