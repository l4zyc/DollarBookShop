module DollarBookShop {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires mysql.connector.java;
    requires java.prefs;
    requires jfxtras.labs;
	requires java.desktop;

    opens main to javafx.fxml, javafx.graphics;
    opens model to javafx.base;
    exports main;
}
