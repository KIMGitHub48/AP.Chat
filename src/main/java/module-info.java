module org {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires reflections;
    requires java.persistence;
    requires java.sql;

    opens org.presentation to javafx.fxml;
    exports org.presentation;
}
