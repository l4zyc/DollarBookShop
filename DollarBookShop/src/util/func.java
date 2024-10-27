package util;

import java.sql.SQLException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public abstract class func {
	private static Connect connect = Connect.getInstance();
	
	public static void showAlert(AlertType at, String title, String content) {
		Alert alert = new Alert(at);
		alert.setTitle(title);
		alert.setContentText(content);
		
		alert.showAndWait();
	}

	public static String getLastId() {
		String query = "SELECT * FROM users "
				+ "ORDER BY UserID "
				+ "DESC LIMIT 1";
		
		connect.rs = connect.execQuery(query);
		String latestID = "";
		try {
			if(!(connect.rs.next())) {
				return "US001";
			}
			
			latestID = connect.rs.getString("UserID");
			String num = latestID.substring(2);
			Integer incr = Integer.parseInt(num) + 1;
			
			latestID = String.format("US%03d", num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return latestID;
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
	
	public static boolean isNotAlphanumeric(String word) {
	    for (int i = 0; i < word.length(); i++) {
	        char chara = word.charAt(i);
	        
	        // Check if character is neither alphabetic nor a digit
	        if (Character.isLetterOrDigit(chara)) {
	            return false;
	        }
	    }
	    return true;
	}
}
