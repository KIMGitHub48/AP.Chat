import apClientUI.AuthorizationService;
import apClientUI.ClientUIServiceImplementation;
import apClientUI.MainClientUIService;
import apClientUI.OptionsService;
import apCommon.apModuleServices.ClientUIService;
import apCommon.apModuleServices.ClientCoreService;

module apClientUI {
    requires javafx.controls;
    requires javafx.fxml;
    requires reflections;
    requires java.persistence;
    requires java.sql;
    requires apCommon;

//    exports apClientAuthorization;
//    exports apClientAuthorization.ServiceImplemintation;
//    exports apClientAuthorization.Controllers;

    opens apClientUI to javafx.fxml;
    //opens apClientAuthorization.Controllers to javafx.fxml;

    uses ClientCoreService;
    uses MainClientUIService;
    uses OptionsService;
    uses AuthorizationService;

    provides ClientUIService with ClientUIServiceImplementation;
}
