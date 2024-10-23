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
}
