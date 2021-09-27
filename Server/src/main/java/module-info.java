module org {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires reflections;
    requires java.sql;

    opens org.Presentation to javafx.fxml;
    exports org.Presentation;
}
