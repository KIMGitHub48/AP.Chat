//import apClientCore.Core.ServiceImplementation.ClientCoreMapServiceImplementations;
//import apClientCore.Core.ServiceImplementation.ClientCoreServiceImplementations;
import apClientCore.Core.MainClientCore;
import apCommon.apModuleServices.ClientCoreMapService;
import apCommon.apModuleServices.ClientCoreService;

module apClient {
//    requires javafx.controls;
//    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires reflections;
    requires java.persistence;
    requires java.sql;
    requires apCommon;
    //requires apAuthorization;

//    opens apClientCore.Presentation to javafx.fxml;
//    exports apClientCore.Presentation;
//    exports apClientCore.Presentation.Controllers;
//    opens apClientCore.Presentation.Controllers to javafx.fxml;

    uses apCommon.apModuleServices.ClientUIService;
//    uses apCommon.apModuleServices.ClientOptionsService;

    provides ClientCoreService with MainClientCore;
//    provides ClientCoreMapService with ClientCoreMapServiceImplementations;
}
