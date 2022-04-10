import apCommon.apModuleServices.ClientAuthorizationService;
import apCommon.apModuleServices.ClientCoreMapService;
import apCommon.apModuleServices.ClientCoreService;
import apCommon.apModuleServices.ClientOptionsService;
import apCommon.apModuleServices.ClientUIService;

module apCommon {
    exports apCommon;
    exports apCommon.apModuleServices;

    uses ClientUIService;
//    uses ClientAuthorizationService;
    //uses ClientOptionsService;
    uses ClientCoreService;
    //uses ClientCoreMapService;
}
