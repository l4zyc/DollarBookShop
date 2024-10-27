package util;

import java.sql.Date;
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
				String UserID = connect.rs.getString("UserID");
				String Email = connect.rs.getString("Email");
				String Username = connect.rs.getString("Username");
				String Password = connect.rs.getString("Password");
				Date DOB = connect.rs.getDate("DOB");
				String Role = connect.rs.getString("Role");
			
				if(Email.equals(email) && password.equals(password)) {
					user = new User(UserID, Email, Username, Password, DOB, Role);
					
					return user;
				}
			}
		} catch (Exception e) {
			System.out.println("User Doesnt exist");
		}
		
		return null;
	}

}
