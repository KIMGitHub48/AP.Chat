//import apClientCore.Core.ServiceImplementation.ClientCoreMapServiceImplementations;
//import apClientCore.Core.ServiceImplementation.ClientCoreServiceImplementations;
import apClientCore.Core.MainClientCoreService;
import apClientCore.Core.ServiceImplementation.ClientCoreServiceImp;
import apClientCore.Core.ServiceImplementation.MainClientCoreServiceImp;
import apCommon.apModuleServices.ClientCoreService;

module apClient {
//    requires javafx.controls;
//    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires reflections;
    requires java.persistence;
    requires java.sql;
    requires apCommon;
    requires apClientUI;
    //requires apAuthorization;

//    opens apClientCore.Presentation to javafx.fxml;
//    exports apClientCore.Presentation;
//    exports apClientCore.Presentation.Controllers;
//    opens apClientCore.Presentation.Controllers to javafx.fxml;

    uses apCommon.apModuleServices.ClientUIService;
    uses MainClientCoreService;
//    uses apCommon.apModuleServices.ClientOptionsService;

//    provides ClientCoreService with MainClientCore;
    provides ClientCoreService with ClientCoreServiceImp;
    provides MainClientCoreService with MainClientCoreServiceImp;
}
