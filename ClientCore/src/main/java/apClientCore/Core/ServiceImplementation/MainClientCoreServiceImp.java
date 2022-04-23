package apClientCore.Core.ServiceImplementation;

import apClientCore.Core.MainClientCore;
import apClientCore.Core.MainClientCoreService;

import java.net.Socket;

public class MainClientCoreServiceImp implements MainClientCoreService {
    public static MainClientCore mainClientCore;
    @Override
    public boolean isAuthorizationAvailable() {
        return mainClientCore.isAuthorizationAvailable();
    }

    @Override
    public void setAuthorizationAvailable(boolean authorizationAvailable) {
        mainClientCore.setAuthorizationAvailable(authorizationAvailable);
    }

    @Override
    public String getLogin() {
        return mainClientCore.getLogin();
    }

    @Override
    public String getPassword() {
        return mainClientCore.getPassword();
    }

    @Override
    public Socket GetServerSocket() {
        return mainClientCore.GetServerSocket();
    }

    @Override
    public void SetLogin(String login) {
        mainClientCore.SetLogin(login);
    }

    @Override
    public void SetPassword(String password) {
        mainClientCore.SetPassword(password);
    }
}
