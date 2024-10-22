package util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public abstract class func {
	public static void showAlert(AlertType at, String title, String content) {
		Alert alert = new Alert(at);
		alert.setTitle(title);
		alert.setContentText(content);
		
		alert.showAndWait();
	}
}
