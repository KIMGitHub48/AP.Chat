import apClientCore.Domain.ServiceImplementation.ClientCoreServiceImplementations;
import apCommon.apModuleServices.ClientCoreService;

module apClient {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires reflections;
    requires java.persistence;
    requires java.sql;
    requires apCommon;
    //requires apAuthorization;

    opens apClientCore.Presentation to javafx.fxml;
    exports apClientCore.Presentation;
    exports apClientCore.Presentation.Controllers;
    opens apClientCore.Presentation.Controllers to javafx.fxml;

    uses apCommon.apModuleServices.ClientAuthorizationService;
    provides ClientCoreService with ClientCoreServiceImplementations;
}
