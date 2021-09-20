module org {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.Presentation to javafx.fxml;
    exports org.Presentation;
}
