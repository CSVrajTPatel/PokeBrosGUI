module pokebros.code {
    requires javafx.controls;
    requires javafx.fxml;

    opens pokebros.code to javafx.fxml;
    exports pokebros.code;
}
