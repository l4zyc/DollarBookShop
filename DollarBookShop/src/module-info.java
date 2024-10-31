module DollarBookShop {
	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	requires mysql.connector.java;
	requires java.prefs;
	
	opens main to javafx.fxml, javafx.graphics;
	opens model to javafx.base;
	
	exports main;
}