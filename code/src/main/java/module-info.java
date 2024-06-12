module pokebros.code {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires javafx.graphics;
    
    opens model to javafx.fxml;

    exports model;

    opens controllers to javafx.fxml;

    exports controllers;

    opens pokebros to javafx.fxml;

    exports pokebros;
}