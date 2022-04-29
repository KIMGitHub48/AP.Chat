import apCommon.apModuleServices.ClientService.ClientCoreService;
import apCommon.apModuleServices.ClientService.ClientUIService;

module apCommon {
    exports apCommon;
    exports apCommon.apModuleServices.ServerService;
    exports apCommon.apModuleServices.ClientService;

    uses ClientUIService;
    uses ClientCoreService;
}
