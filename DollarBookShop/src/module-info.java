module DollarBookShop {
	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	
	opens main to javafx.fxml, javafx.graphics;
	
	exports main;
}