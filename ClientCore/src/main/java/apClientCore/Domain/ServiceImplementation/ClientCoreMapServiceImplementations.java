package apClientCore.Domain.ServiceImplementation;
import apCommon.apModuleServices.ClientCoreMapService;
import apCommon.apModuleServices.ClientOptionsService;

public class ClientCoreMapServiceImplementations implements ClientCoreMapService {
    private apCommon.apModuleServices.ClientOptionsService clientOptionsService = ClientOptionsService.getFirst();

    @Override
    public void ShowOptionsStage() {
        clientOptionsService.ShowOptionsStage();
    }
}
