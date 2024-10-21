module DollarBookShop {
	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	requires mysql.connector.java;
	
	opens main to javafx.fxml, javafx.graphics;
	
	exports main;
}