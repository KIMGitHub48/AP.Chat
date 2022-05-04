import apCommon.apModuleServices.ClientService.ClientCoreService;
import apCommon.apModuleServices.ClientService.ClientUIService;
import apCommon.apModuleServices.ServerService.ServerDATAService;

module apCommon {
    exports apCommon;
    exports apCommon.apModuleServices.ServerService;
    exports apCommon.apModuleServices.ClientService;

    uses ClientUIService;
    uses ClientCoreService;
    uses ServerDATAService;
}
