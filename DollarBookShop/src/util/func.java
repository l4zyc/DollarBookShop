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
	
	public static boolean validateEmail(String email) {
		Integer atIndex = email.indexOf('@');
		
		if(atIndex == -1) {
			return false;
		}
		
		Integer dotIndex = email.indexOf('.', atIndex);
		Integer domainIndex = email.indexOf("gmail", atIndex);
		
		if(dotIndex == -1) {
			return false;
		}
		
		if(dotIndex == 0 || atIndex == email.length() - 1) {
			return false;
		}
		
		if(domainIndex > dotIndex) {
			return false;
		}
		
		
		return true;
	}
	
	public static boolean isAlphanumeric(String word) {
		for(int i = 0; i < word.length(); i++) {
			Character chara = word.charAt(i);
			
			if(!(Character.isAlphabetic(chara)) || !(Character.isDigit(chara))) {
				return false;
			}
		}
		
		return true;
	}
}
