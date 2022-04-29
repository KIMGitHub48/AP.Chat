import apServerCore.DATA.DATAService;
import apServerCore.DATA.DATAServiceImp;
import apCommon.apModuleServices.ServerService.*;

module apServerCore {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires reflections;
    requires java.sql;
    requires derby;
    requires apCommon;

    opens apServerCore.Presentation to javafx.fxml;
    exports apServerCore.Presentation;
    opens apServerCore.Presentation.Controllers to javafx.fxml;
    exports apServerCore.Presentation.Controllers;

    uses DATAService;
    uses ServerUIService;
    uses ServerDATAService;

    provides DATAService with DATAServiceImp;
}
