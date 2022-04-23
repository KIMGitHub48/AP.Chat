import ap.DATA.DATAService;
import ap.DATA.DATAServiceImp;

module ap {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires reflections;
    requires java.sql;
    requires derby;
    requires apCommon;

    opens ap.Presentation to javafx.fxml;
    exports ap.Presentation;
    opens ap.Presentation.Controllers to javafx.fxml;
    exports ap.Presentation.Controllers;

    uses DATAService;

    provides DATAService with DATAServiceImp;
}
