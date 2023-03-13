module com.example.perfumeshop {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires java.desktop;

    opens com.example.perfumeshop to javafx.fxml;
    exports com.example.perfumeshop;
    exports com.example.perfumeshop.view;
    opens com.example.perfumeshop.view to javafx.fxml;
}