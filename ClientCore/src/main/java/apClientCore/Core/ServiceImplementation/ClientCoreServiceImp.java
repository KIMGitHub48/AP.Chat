package apClientCore.Core.ServiceImplementation;

import apClientCore.Core.MainClientCore;
import apCommon.ApMessage;
import apCommon.apModuleServices.ClientService.ClientCoreService;

public class ClientCoreServiceImp implements ClientCoreService {
    public static MainClientCore mainClientCore;

    @Override
    public boolean IsConnected() {
        return mainClientCore.IsConnected();
    }

    @Override
    public void ConnectToServer() {
        mainClientCore.ConnectToServer();
    }

    @Override
    public void SetLogin(String login) {
        mainClientCore.SetLogin(login);
    }

    @Override
    public void SetPassword(String password) {
        mainClientCore.SetPassword(password);
    }

    @Override
    public boolean isAuthorizationAvailable() {
        return mainClientCore.isAuthorizationAvailable();
    }

    @Override
    public void AuthorizationButtonEnterInThread() {
        mainClientCore.AuthorizationButtonEnterInThread();
    }

    @Override
    public void SetAuthorizationAvailable(boolean authorizationAvailable) {
        mainClientCore.setAuthorizationAvailable(authorizationAvailable);
    }

    @Override
    public void SendMessage(ApMessage apMessage) {
        mainClientCore.SendMessage(apMessage);
    }
}
