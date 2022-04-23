import apClientUI.*;
//import apClientUI.ClientUIServiceImplementation;
import apClientUI.apAuthorization.AuthorizationServiceImp;
import apClientUI.apOptions.OptionsServiceImp;
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
    exports apClientUI.apAuthorization.Controllers;
    exports apClientUI.apOptions;
    exports apClientUI.apOptions.Controllers;
    exports apClientUI;

    opens apClientUI to javafx.fxml;
    opens apClientUI.apAuthorization.Controllers to javafx.fxml;
    opens apClientUI.apOptions.Controllers to javafx.fxml;

    uses ClientCoreService;
    uses MainClientUIService;
    uses OptionsService;
    uses AuthorizationService;

    provides ClientUIService with ClientUIServiceImp;
    provides AuthorizationService with AuthorizationServiceImp;
    provides MainClientUIService with MainClientUIServiceImp;
    provides OptionsService with OptionsServiceImp;
//    provides ClientUIService with ClientUIServiceImplementation;
    //provides MainClientUIService with MainClientUI;
}
