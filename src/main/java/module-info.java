module com.example.wnioskowanie {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires owlapi.api;
    requires owlapi.apibinding;
    requires org.semanticweb.hermit;

    opens com.example.wnioskowanie to javafx.fxml;
    exports com.example.wnioskowanie;
    exports com.company;
    opens com.company to javafx.fxml;
}