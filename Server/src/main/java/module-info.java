module ap {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires reflections;
    requires java.sql;
    requires derby;

    opens ap.Presentation to javafx.fxml;
    exports ap.Presentation;
    opens ap.Presentation.Controllers to javafx.fxml;
    exports ap.Presentation.Controllers;
}
