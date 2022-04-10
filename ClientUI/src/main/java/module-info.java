import apClientUI.AuthorizationService;
//import apClientUI.ClientUIServiceImplementation;
import apClientUI.MainClientUI;
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

    exports apClientUI.apAuthorization;
    exports apClientUI.apAuthorization.ServiceImplementation;
    exports apClientUI.apAuthorization.Controllers;

    opens apClientUI to javafx.fxml;
    opens apClientUI.apAuthorization.Controllers to javafx.fxml;

    uses ClientCoreService;
    uses MainClientUIService;
    uses OptionsService;
    uses AuthorizationService;

    provides ClientUIService with MainClientUI;
    //provides MainClientUIService with MainClientUI;
}
