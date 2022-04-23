package apClientUI;

import apCommon.apModuleServices.ClientUIService;

public class ClientUIServiceImp implements ClientUIService{
    private AuthorizationService authorizationService = AuthorizationService.getFirst();
    private MainClientUIService mainClientUIService = MainClientUIService.getFirst();
    private OptionsService optionsService = OptionsService.getFirst();

    @Override
    public void Launcher(String[] args) {
        mainClientUIService.Launcher(args);
    }

    @Override
    public void AuthorizationResponseActionConnectionError() {
        authorizationService.AuthorizationResponseActionConnectionError();
    }

    @Override
    public void AuthorizationResponseActionTimeError() {
        authorizationService.AuthorizationResponseActionTimeError();
    }

    @Override
    public void AuthorizationResponseActionNotPassed() {
        authorizationService.AuthorizationResponseActionNotPassed();
    }

    @Override
    public void AuthorizationResponseActionPassed() {
        authorizationService.AuthorizationResponseActionPassed();
    }

    @Override
    public String GetServerIP() {
        return optionsService.GetServerIP();
    }

    @Override
    public Integer GetServerPort() {
        return optionsService.GetServerPort();
    }
}
