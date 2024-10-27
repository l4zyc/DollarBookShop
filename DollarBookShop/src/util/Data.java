package util;

import java.sql.SQLException;

import model.User;

public class Data {
	
	private Connect connect = Connect.getInstance();
	
	public User getUserData(String email, String password) throws NullPointerException { 
		
		String query = "SELECT * FROM MsUser";
		
		connect.rs = connect.execQuery(query);
		User user = null;
		
		try {
			while(connect.rs.next()) {
				
			}
		} catch (Exception e) {
			System.out.println("User Doesnt exist");
		}
		
		return null;
	}

}
