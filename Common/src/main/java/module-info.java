import apCommon.apModuleServices.ClientAuthorizationService;
import apCommon.apModuleServices.ClientCoreService;

module apCommon {
    exports apCommon;
    exports apCommon.apModuleServices;

    uses ClientAuthorizationService;
    uses ClientCoreService;
}
