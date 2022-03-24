import apClient.Domain.TestInterfaceR;

module apClient {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires reflections;
    requires java.persistence;
    requires java.sql;
    requires apCommon;

    opens apClient.Presentation to javafx.fxml;
    exports apClient.Presentation;
    exports apClient.Presentation.Controllers;
    opens apClient.Presentation.Controllers to javafx.fxml;

    //exports apClient.Domain to apCommon;
    provides apCommon.TestInterface with TestInterfaceR;
}
