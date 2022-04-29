package apServerUI;

import apCommon.apModuleServices.ServerService.*;

public class ServerUIServiceImp implements ServerUIService {
    private MainServerUIService mainServerUIService = MainServerUIService.getFirst();

    @Override
    public void Launcher(String[] args) {
        mainServerUIService.Launcher(args);
    }

    @Override
    public Integer GetServerPort() {
        return null;
    }
}
