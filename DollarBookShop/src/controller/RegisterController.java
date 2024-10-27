package controller;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.scene.control.ListView;
import model.User;
import util.Connect;
import util.func;
import view.RegisterView;

public class RegisterController {
	
	private RegisterView view;
	private Connect connect = Connect.getInstance();

	public RegisterController(RegisterView view) {
		this.view = view;
		onActionHandler();
	}
	
	public void onActionHandler() {
		
	}
	
	public ArrayList<User> getData() {
		String query = "SELECT * FROM users";
		
		
		ArrayList<User> users = new ArrayList<User>();
		connect.rs = connect.execQuery(query);
		
		try {
			while(connect.rs.next()) {
				String ID = connect.rs.getString("UserID");
				String email = connect.rs.getString("Email");
				String username = connect.rs.getString("Username");
				String passwd = connect.rs.getString("Password");
				Date date = connect.rs.getDate("DOB");
				String role = connect.rs.getString("Role");
				
				users.add(new User(ID, email, username, passwd, date, role));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return users;
	}
	
	public String newID() {
		String query = "SELECT UserID FROM users "
				+ "ORDER BY UserID "
				+ "DESC LIMIT 1";
		
		connect.rs = connect.execQuery(query);
		String ID = null;
		
		try {
			if(!(connect.rs.next())) {
				return "US001";
			}
			ID = connect.rs.getString("ID");
			String IDnum = ID.substring(2);
			Integer num = Integer.parseInt(IDnum) + 1;
			ID = String.format("US%03d", num);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ID;
	}
	
	public void insertData() {
		
	}
	
	public void validateRegister() {
		ArrayList<User> users = getData();
		String email = view.getEmailField().getText();
		String username = view.getUsernameField().getText();
		String password = view.getPasswdField().getText();
		String confPasswd = view.getConfPasswdField().getText();
		LocalDate localDate = view.getDOBPicker().getValue();
		
		if(!func.validateEmail(email)) {
			return;
		}
		
		if(username.length() < 5 && !(IsUsernameUnique(users, username))) {
			return;
		}
		
		if(password.length() < 8 && func.isAlphanumeric(password)) {
			return;
		}
		
		if(!confPasswd.equals(password)) {
			return;
		}
		
		if(validateAge(18, localDate)) {
			return;
		}
		
		

		
	}
	
	public boolean IsUsernameUnique(ArrayList<User> users, String username) {
		for (User user : users) {
			if(user.getUsername().equals(username)) {
				return false;
			}
		}
		
		return true;
	}
	
	public boolean isEmailUnique(ArrayList<User> users, String email) {
		for (User user : users) {
			if(user.getEmail().equals(email)) {
				return false;
			}
		}
		
		return true;
	}
	
	
	public boolean validateAge(Integer minAge, LocalDate DOB) {
		
		LocalDate currDate =LocalDate.now();
		
		Integer birthYear = DOB.getYear();
		Integer currYear = currDate.getYear();
		
		Integer age = currYear - birthYear;
		
		if(age > minAge) {
			return false;
		}
		
		if(age == minAge) {
            if (currDate.getMonthValue() > DOB.getMonthValue() ||
                    (currDate.getMonthValue() == DOB.getMonthValue() && currDate.getDayOfMonth() >= DOB.getDayOfMonth())) {
                    return false;
                }
		}
		
		return true;
	}
}
