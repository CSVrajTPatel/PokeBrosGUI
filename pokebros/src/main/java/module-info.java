module pokebros.code {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    
    opens model to javafx.fxml;

    exports model;

    opens controllers to javafx.fxml;

    exports controllers;

    opens pokebros.code to javafx.fxml;

    exports pokebros.code;
}
