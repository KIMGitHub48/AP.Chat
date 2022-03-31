import apClientOptions.ServiceImplemintation.ClientOptionsImplementation;
import apCommon.apModuleServices.ClientCoreMapService;
import apCommon.apModuleServices.ClientOptionsService;
import apCommon.apModuleServices.ClientCoreService;

module apClientOptions {
    requires javafx.controls;
    requires javafx.fxml;
    requires reflections;
    requires java.persistence;
    requires java.sql;
    requires apCommon;

    exports apClientOptions;
    exports apClientOptions.ServiceImplemintation;
    exports apClientOptions.Controllers;

    opens apClientOptions to javafx.fxml;
    opens apClientOptions.Controllers to javafx.fxml;

    uses ClientCoreService;
    uses ClientCoreMapService;

    provides ClientOptionsService with ClientOptionsImplementation;
}
