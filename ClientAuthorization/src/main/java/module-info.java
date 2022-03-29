import apClientAuthorization.ServiceImplemintation.ClientAuthorizationImplementation;
import apCommon.apModuleServices.ClientAuthorizationService;
import apCommon.apModuleServices.ClientCoreService;

module apAuthorization {
    uses ClientCoreService;

    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires reflections;
    requires java.persistence;
    requires java.sql;
    requires apCommon;

    exports apClientAuthorization;
    exports apClientAuthorization.ServiceImplemintation;
    exports apClientAuthorization.Controllers;

    opens apClientAuthorization to javafx.fxml;
    opens apClientAuthorization.Controllers to javafx.fxml;

    provides ClientAuthorizationService with ClientAuthorizationImplementation;
}
