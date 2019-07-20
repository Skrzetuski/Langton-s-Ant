open module JavaFX11Template {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.swing;
    requires java.desktop;
    requires static lombok;

    exports application;
}