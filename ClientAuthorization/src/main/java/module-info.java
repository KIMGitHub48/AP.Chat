import apClientAuthorization.ServiceImplemintation.ClientAuthorizationImplementation;
import apCommon.apModuleServices.ClientAuthorizationService;
import apCommon.apModuleServices.ClientCoreMapService;
import apCommon.apModuleServices.ClientCoreService;

module apAuthorization {
    requires javafx.controls;
    requires javafx.fxml;
    requires reflections;
    requires java.persistence;
    requires java.sql;
    requires apCommon;

    exports apClientAuthorization;
    exports apClientAuthorization.ServiceImplemintation;
    exports apClientAuthorization.Controllers;

    opens apClientAuthorization to javafx.fxml;
    opens apClientAuthorization.Controllers to javafx.fxml;

    uses ClientCoreService;
    uses ClientCoreMapService;

    provides ClientAuthorizationService with ClientAuthorizationImplementation;
}
