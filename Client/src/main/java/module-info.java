module ap {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires reflections;
    requires java.persistence;
    requires java.sql;

    opens ap.Presentation to javafx.fxml;
    exports ap.Presentation;
}
