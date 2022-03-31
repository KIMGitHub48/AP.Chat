import apCommon.apModuleServices.ClientAuthorizationService;
import apCommon.apModuleServices.ClientCoreMapService;
import apCommon.apModuleServices.ClientCoreService;
import apCommon.apModuleServices.ClientOptionsService;

module apCommon {
    exports apCommon;
    exports apCommon.apModuleServices;

    uses ClientAuthorizationService;
    uses ClientOptionsService;
    uses ClientCoreService;
    uses ClientCoreMapService;
}
